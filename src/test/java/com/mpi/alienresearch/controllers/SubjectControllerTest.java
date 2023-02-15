package com.mpi.alienresearch.controllers;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mpi.alienresearch.DemoApplication;
import com.mpi.alienresearch.model.Subject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = DemoApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SubjectControllerTest {

    @Autowired
    private MockMvc mvc;

    ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testAddSubject() throws Exception {
        MvcResult result = mvc.perform(get("/subjects")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<Subject> subjects = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Subject.class));
        assertEquals(3, subjects.size());

        Subject u = new Subject();
        u.setBirthDate(LocalDate.of(1983, 4, 25));
        u.setEyesColor("bright0");
        u.setHairColor("red0");
        u.setHeight(1.6);
        u.setWeight(65.0);
        u.setName("New");

        mvc.perform(post("/subjects")
            .content(mapper.writeValueAsString(u))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        result = mvc.perform(get("/subjects")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        res = result.getResponse().getContentAsString();
        subjects = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Subject.class));
        assertEquals(4, subjects.size());
        u = subjects.get(3);
        assertEquals("red0", u.getHairColor());
        assertEquals(1.6, u.getHeight().doubleValue(), 0.001);
        assertEquals(LocalDate.of(1983, 4, 25), u.getBirthDate());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAll() throws Exception {
        MvcResult result = mvc.perform(get("/subjects")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<Subject> users = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Subject.class));
        assertEquals(3, users.size());
        Subject u = users.stream().filter(o -> o.getId().longValue() == 2).findFirst().get();
        assertEquals("black", u.getHairColor());
        assertEquals(1.7, u.getHeight().doubleValue(), 0.001);
        assertEquals(LocalDate.of(1985, 8, 15), u.getBirthDate());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetSubjectById() throws Exception {
        MvcResult result = mvc.perform(get("/subjects/2")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        Subject u = mapper.readValue(res, Subject.class);
        assertEquals("black", u.getHairColor());
        assertEquals(1.7, u.getHeight().doubleValue(), 0.001);
        assertEquals(LocalDate.of(1985, 8, 15), u.getBirthDate());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateSubject() throws Exception {
        MvcResult result = mvc.perform(get("/subjects/2")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        Subject u = mapper.readValue(res, Subject.class);
        assertEquals("black", u.getHairColor());
        assertNull(u.getSkinColor());
        assertEquals(1.7, u.getHeight().doubleValue(), 0.001);

        u.setHairColor("pale");
        u.setSkinColor("brown");

        mvc.perform(put("/subjects/2")
            .content(mapper.writeValueAsString(u))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andReturn();

        result = mvc.perform(get("/subjects/2")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        u = mapper.readValue(res, Subject.class);
        assertEquals("pale", u.getHairColor());
        assertEquals("brown", u.getSkinColor());
        assertEquals(1.7, u.getHeight().doubleValue(), 0.001);
    }
}
