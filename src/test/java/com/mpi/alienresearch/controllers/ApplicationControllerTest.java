package com.mpi.alienresearch.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.mpi.alienresearch.model.AppAnalysis;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.Subject;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = DemoApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ApplicationControllerTest {

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
    public void testAcceptApplication() throws Exception {
        MvcResult result = mvc.perform(get("/applications/7")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        String res = result.getResponse().getContentAsString();
        Application u = mapper.readValue(res, Application.class);
        
        assertEquals(AppStatus.APPROVED, u.getStatus());

        mvc.perform(post("/applications/{id}/accept", u.getId())
            .param("ug", "105")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        result = mvc.perform(get("/applications/7")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        u = mapper.readValue(res, Application.class);
        
        assertEquals(AppStatus.ACCEPTED, u.getStatus());
        assertEquals("105", u.getExecutionGroup());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddReport() throws Exception {
        MvcResult result = mvc.perform(get("/reports?application=5")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<Report> objects = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Report.class));

        assertEquals(0, objects.size());

        Report u = new Report();
        u.setContent("content");
        u.setTitle("title");
        
        mvc.perform(post("/applications/5/reports")
            .content(mapper.writeValueAsString(u))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isAccepted())
            .andReturn();

        result = mvc.perform(get("/reports?application=5")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        objects = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Report.class));
        assertEquals(1, objects.size());
        u = objects.get(0);
        assertEquals("title", u.getTitle());
        assertEquals(5, u.getApplication().getId().longValue());
        assertNull(u.getExperiment());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDecisionApprovedApplication() throws Exception {
        MvcResult result = mvc.perform(get("/applications/6")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        String res = result.getResponse().getContentAsString();
        AppAnalysis u = mapper.readValue(res, AppAnalysis.class);
        
        assertEquals(AppStatus.CREATED, u.getStatus());

        mvc.perform(post("/applications/{id}/set-status", u.getId())
            .param("status", "APPROVED")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        result = mvc.perform(get("/applications/6")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        u = mapper.readValue(res, AppAnalysis.class);
        
        assertEquals(AppStatus.APPROVED, u.getStatus());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDecisionDeclinedApplication() throws Exception {
        MvcResult result = mvc.perform(get("/applications/6")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        String res = result.getResponse().getContentAsString();
        AppAnalysis u = mapper.readValue(res, AppAnalysis.class);
        
        assertEquals(AppStatus.CREATED, u.getStatus());

        mvc.perform(post("/applications/{id}/set-status", u.getId())
            .param("status", "DECLINED")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        result = mvc.perform(get("/applications/6")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        u = mapper.readValue(res, AppAnalysis.class);
        
        assertEquals(AppStatus.DECLINED, u.getStatus());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAll() throws Exception {
        MvcResult result = mvc.perform(get("/applications")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        List<Application> objects = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Application.class));
        assertEquals(4, objects.size());
        Application u = objects.stream().filter(o -> o.getId().longValue() == 6).findFirst().get();
        assertEquals("anl_app_1", u.getDescription());
        assertEquals(AppType.ANALYSIS, u.getType());
        assertEquals(AppStatus.CREATED, u.getStatus());
        assertEquals(LocalDateTime.of(2020, 4, 26,0,0,0), u.getCreationDate());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetApplicationById() throws Exception {
        MvcResult result = mvc.perform(get("/applications/6")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        AppAnalysis u = mapper.readValue(res, AppAnalysis.class);
        assertEquals("anl_app_1", u.getDescription());
        assertEquals(AppType.ANALYSIS, u.getType());
        assertEquals(AppStatus.CREATED, u.getStatus());
        assertEquals(LocalDateTime.of(2020, 4, 26,0,0,0), u.getCreationDate());
        assertEquals("desc_analysis_1", u.getAnalysisDescription());
        assertEquals(1, u.getSubject().getId().longValue());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateApplication() throws Exception {
        MvcResult result = mvc.perform(get("/applications/6")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        AppAnalysis u = mapper.readValue(res, AppAnalysis.class);
        assertEquals("anl_app_1", u.getDescription());
        assertEquals(AppType.ANALYSIS, u.getType());
        assertEquals(AppStatus.CREATED, u.getStatus());
        assertEquals(LocalDateTime.of(2020, 4, 26,0,0,0), u.getCreationDate());
        assertEquals("desc_analysis_1", u.getAnalysisDescription());
        assertEquals(1, u.getSubject().getId().longValue());

        u.setAnalysisDescription("new_desc");

        result = mvc.perform(put("/applications/6")
            .content(mapper.writeValueAsString(u))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andReturn();
        
        result = mvc.perform(get("/applications/6")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        res = result.getResponse().getContentAsString();
        u = mapper.readValue(res, AppAnalysis.class);
        assertEquals(LocalDateTime.of(2020, 4, 26,0,0,0), u.getCreationDate());
        assertEquals("new_desc", u.getAnalysisDescription());
        assertEquals(1, u.getSubject().getId().longValue());
    }
}
