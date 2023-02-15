package com.mpi.alienresearch.controllers;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mpi.alienresearch.DemoApplication;
import com.mpi.alienresearch.dao.UserDao;
import com.mpi.alienresearch.model.Credentials;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.model.enums.UserRole;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = DemoApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserDao userDao;

    ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddUser() throws Exception {
        Credentials cr = new Credentials("lander_test", "lander_test");
        mvc.perform(post("/login")
            .content(mapper.writeValueAsString(cr))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());

        User u = new User();
        u.setPassword("lander_test");
        u.setUsername("lander_test");
        u.setRole(UserRole.LANDER);
        u.setJobAgreementNumber("1000020");
        u.setFirstName("LanderT");
        u.setLastName("Down");
        u.setPhoneNumber("0-040-40-01");
        u.setBirthDate(LocalDate.of(2003, 5, 15));
        
        mvc.perform(post("/users")
            .content(mapper.writeValueAsString(u))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        MvcResult result = mvc.perform(post("/login")
            .content(mapper.writeValueAsString(cr))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        String res = result.getResponse().getContentAsString();
        User u_res = mapper.readValue(res, User.class);
        assertEquals(UserRole.LANDER, u_res.getRole());
        assertEquals("LanderT", u_res.getFirstName());
        assertEquals("lander_test", u_res.getUsername());
        assertEquals(LocalDate.of(2003, 5, 15), u_res.getBirthDate());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetById() throws Exception {
        MvcResult result = mvc.perform(get("/users/5")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        User u = mapper.readValue(res, User.class);
        assertEquals(UserRole.LANDER, u.getRole());
        assertEquals("Lander", u.getFirstName());
        assertEquals("lander", u.getUsername());
        assertEquals(LocalDate.of(2003, 4, 15), u.getBirthDate());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetUsers() throws Exception {
        MvcResult result = mvc.perform(get("/users")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<User> users = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, User.class));
        assertEquals(7, users.size());
        User u = users.stream().filter(o -> o.getId().longValue() == 3).findFirst().get();
        assertEquals(UserRole.ADMIN, u.getRole());
        assertEquals("Admin", u.getFirstName());
        assertEquals("admin", u.getUsername());
        assertEquals(LocalDate.of(1999, 6, 25), u.getBirthDate());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateUser() throws Exception {
        MvcResult result = mvc.perform(get("/users/5")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        User u = mapper.readValue(res, User.class);
        assertEquals(UserRole.LANDER, u.getRole());
        assertEquals("lander", u.getUsername());
        assertEquals("0-000-00-01", u.getPhoneNumber());
        assertNull(u.getEmail());

        u.setEmail("example@r.com");
        u.setPhoneNumber("552-22-333");

        mvc.perform(put("/users/5")
            .content(mapper.writeValueAsString(u))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andReturn();

        result = mvc.perform(get("/users/5")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        res = result.getResponse().getContentAsString();
        u = mapper.readValue(res, User.class);
        assertEquals(UserRole.LANDER, u.getRole());
        assertEquals("lander", u.getUsername());
        assertEquals("552-22-333", u.getPhoneNumber());
        assertEquals("example@r.com", u.getEmail());
    }
}
