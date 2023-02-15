package com.mpi.alienresearch.controllers;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mpi.alienresearch.DemoApplication;
import com.mpi.alienresearch.dao.ApplicationDao;
import com.mpi.alienresearch.dao.ReportDao;
import com.mpi.alienresearch.model.AppTechnic;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;
import com.mpi.alienresearch.model.enums.ExperimentStatus;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = DemoApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ExperimentControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ReportDao reportDao;
    
    @Autowired
    private ApplicationDao applicationDao;

    ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    // @Transactional
    @Rollback(true)
    public void testAddApplicationTechnic() throws Exception {
        Experiment u = new Experiment();
        u.setId(3l);
        
        AppTechnic r = new AppTechnic();
        r.setType(AppType.TECHNIC);
        User user = new User();
        user.setId(6l);
        r.setCreator(user);
        r.setContent("content");
        
        MvcResult result = mvc.perform(post("/experiments/3/applications")
            .content(mapper.writeValueAsString(r))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isAccepted())
            .andReturn();

        String res = result.getResponse().getRedirectedUrl();
        String app_id = res.split("/")[2];
        
        result = mvc.perform(get(res)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        r = mapper.readValue(res, AppTechnic.class);
        assertEquals(AppType.TECHNIC, r.getType());
        assertEquals("content", r.getContent());
        assertNotNull(r.getCreationDate());
        assertEquals(3l, r.getExperiment().getId().longValue());
        assertEquals(AppStatus.CREATED, r.getStatus());

        applicationDao.deleteById(Long.parseLong(app_id));
        
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddExperiment() throws Exception {
        MvcResult result = mvc.perform(get("/experiments")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<Experiment> experiments = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Experiment.class));
        assertEquals(3, experiments.size());

        Experiment u = new Experiment();
        u.setTitle("ExpNew");
        u.setResearchGroup("2");

        mvc.perform(post("/experiments")
            .content(mapper.writeValueAsString(u))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isAccepted());

        result = mvc.perform(get("/experiments")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        res = result.getResponse().getContentAsString();
        experiments = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Experiment.class));
        assertEquals(4, experiments.size());

        u = experiments.get(3);
        assertEquals("ExpNew", u.getTitle());
        assertNull(u.getDescription());
        assertNotNull(u.getCreationTime());
        assertEquals(ExperimentStatus.CREATED, u.getStatus());
    }

    @Test
    // @Transactional
    @Rollback(true)
    public void testAddReport() throws Exception {
        Experiment u = new Experiment();
        u.setId(3l);
        
        Report r = new Report();
        r.setTitle("newReport");
        
        MvcResult result = mvc.perform(post("/experiments/3/reports")
            .content(mapper.writeValueAsString(r))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isAccepted())
            .andReturn();

        String res = result.getResponse().getRedirectedUrl();
        String rep_id = res.split("/")[2];

        result = mvc.perform(get(res)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        r = mapper.readValue(res, Report.class);
        assertEquals("newReport", r.getTitle());
        assertNull(r.getContent());
        assertNotNull(r.getCreationDate());
        assertEquals(3l, r.getExperiment().getId().longValue());

        // clear new report
        reportDao.deleteById(Long.parseLong(rep_id));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAll() throws Exception {
        MvcResult result = mvc.perform(get("/experiments")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<Experiment> users = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Experiment.class));
        assertEquals(3, users.size());
        Experiment u = users.stream().filter(o -> o.getId().longValue() == 3).findFirst().get();
        assertEquals("Exp3", u.getTitle());
        assertEquals("desc3", u.getDescription());
        assertEquals(LocalDateTime.of(2020, 4, 27, 0, 0, 0), u.getCreationTime());
        assertEquals(ExperimentStatus.IN_PROGRESS, u.getStatus());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetExperimentById() throws Exception {
        MvcResult result = mvc.perform(get("/experiments/3")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        Experiment u = mapper.readValue(res, Experiment.class);
        assertEquals("Exp3", u.getTitle());
        assertEquals("desc3", u.getDescription());
        assertEquals(LocalDateTime.of(2020, 4, 27, 0, 0, 0), u.getCreationTime());
        assertEquals(ExperimentStatus.IN_PROGRESS, u.getStatus());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSearchExperimentByGroup() throws Exception {
        MvcResult result = mvc.perform(get("/experiments").param("researchGroup", "1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<Experiment> users = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Experiment.class));
        assertEquals(2, users.size());
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testSearchExperimentByTitle() throws Exception {
        MvcResult result = mvc.perform(get("/experiments").param("title", "Exp")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<Experiment> users = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Experiment.class));
        assertEquals(3, users.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateExperiment() throws Exception {
        MvcResult result = mvc.perform(get("/experiments/3")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andReturn();
    
        String res = result.getResponse().getContentAsString();
        Experiment u = mapper.readValue(res, Experiment.class);
        assertEquals("Exp3", u.getTitle());
        assertEquals("desc3", u.getDescription());
        assertEquals(LocalDateTime.of(2020, 4, 27, 0, 0, 0), u.getCreationTime());
        assertEquals(ExperimentStatus.IN_PROGRESS, u.getStatus());

        u.setStatus(ExperimentStatus.FINISHING);
        u.setDescription("NewDesc");

        mvc.perform(put("/experiments/3")
            .content(mapper.writeValueAsString(u))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andReturn();

        result = mvc.perform(get("/experiments/3")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        u = mapper.readValue(res, Experiment.class);
        assertEquals("Exp3", u.getTitle());
        assertEquals("NewDesc", u.getDescription());
        assertEquals(LocalDateTime.of(2020, 4, 27, 0, 0, 0), u.getCreationTime());
        assertEquals(ExperimentStatus.FINISHING, u.getStatus());
    }
}
