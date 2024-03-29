package com.mpi.alienresearch.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
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
import com.mpi.alienresearch.model.Artifact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = DemoApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ArtifactControllerTest {

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
    public void testAddArtifact() throws Exception {
        MvcResult result = mvc.perform(get("/artifacts")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<Artifact> artifacts = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Artifact.class));
        assertEquals(3, artifacts.size());

        Artifact u = new Artifact();
        u.setName("Extra");
        u.setDescription("desc4");
        u.setRadiation(15.2);
        
        mvc.perform(post("/artifacts")
            .content(mapper.writeValueAsString(u))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        result = mvc.perform(get("/artifacts")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        artifacts = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Artifact.class));
        assertEquals(4, artifacts.size());

        u = artifacts.get(3);
        assertEquals("Extra", u.getName());
        assertEquals("desc4", u.getDescription());
        assertEquals(15.2, u.getRadiation().doubleValue(), 0.01);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAll() throws Exception {
        MvcResult result = mvc.perform(get("/artifacts")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<Artifact> users = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Artifact.class));
        assertEquals(3, users.size());
        Artifact u = users.stream().filter(o -> o.getId().longValue() == 3).findFirst().get();
        assertEquals("Bread", u.getName());
        assertEquals("desc3", u.getDescription());
        assertEquals(0.0, u.getRadiation().doubleValue(), 0.01);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetArtifactById() throws Exception {
        MvcResult result = mvc.perform(get("/artifacts/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        Artifact u = mapper.readValue(res, Artifact.class);
        assertEquals("Empty", u.getName());
        assertEquals("desc1", u.getDescription());
        assertEquals(10.0, u.getRadiation().doubleValue(), 0.01);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateArtifact() throws Exception {
        MvcResult result = mvc.perform(get("/artifacts/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        Artifact u = mapper.readValue(res, Artifact.class);
        assertEquals("Empty", u.getName());
        assertEquals("desc1", u.getDescription());
        assertEquals(10.0, u.getRadiation().doubleValue(), 0.01);

        u.setRadiation(55.66);
        u.setDescription("new desc");

        mvc.perform(put("/artifacts/1")
            .content(mapper.writeValueAsString(u))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andReturn();

        result = mvc.perform(get("/artifacts/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        u = mapper.readValue(res, Artifact.class);
        assertEquals("Empty", u.getName());
        assertEquals("new desc", u.getDescription());
        assertEquals(55.66, u.getRadiation().doubleValue(), 0.01);
    }
}
