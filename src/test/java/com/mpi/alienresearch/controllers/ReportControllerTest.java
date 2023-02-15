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
public class ReportControllerTest {
    
    @Autowired
    private MockMvc mvc;

    ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testGetAll() throws Exception {
        MvcResult result = mvc.perform(get("/reports")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        String res = result.getResponse().getContentAsString();
        List<Report> objects = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, Report.class));

        assertEquals(4, objects.size());
    }

    @Test
    public void testGetReport() throws Exception {
        MvcResult result = mvc.perform(get("/reports/4")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        String res = result.getResponse().getContentAsString();
        Report object = mapper.readValue(res, Report.class);

        assertEquals("Rep2", object.getTitle());
        assertEquals(2, object.getExperiment().getId().longValue());
        assertNull(object.getApplication());
    }
}
