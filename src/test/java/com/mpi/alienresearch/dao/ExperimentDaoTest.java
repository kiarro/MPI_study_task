package com.mpi.alienresearch.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.UserGroup;
import com.mpi.alienresearch.model.enums.ExperimentStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExperimentDaoTest {
    @Autowired
    private ExperimentDao experimentDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testFindByResearchGroup() {
        List<Experiment> lista = experimentDao.findByResearchGroup(new UserGroup(2l));
        assertEquals(lista.size(), 3);
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testAddExperiment() {
        Experiment a = new Experiment();
        a.setCreationTime(LocalDateTime.now());
        a.setStatus(ExperimentStatus.CREATED);
        a.setTitle("new exp");

        Experiment a1 = experimentDao.save(a);
        assertEquals(a1.getId(), a.getId());
        assertEquals(a1.getStatus(), a.getStatus());
        assertEquals(a1.getTitle(), a.getTitle());
        assertEquals(a1.getCreationTime(), a.getCreationTime());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAll() {
        List<Experiment> lista = experimentDao.findAll();
        assertEquals(lista.size(), 4);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetExperiment() {
        Experiment a = experimentDao.getOne(2l);
        assertEquals(a.getId().longValue(), 2l);
        assertEquals(a.getStatus(), ExperimentStatus.FINISHED);
        assertEquals(a.getTitle(), "ex2");
        assertEquals(a.getCreationTime(), LocalDateTime.of(2023, 01, 15, 0, 0, 0));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetExists() {
        boolean ex = experimentDao.existsById(2l);
        assertTrue(ex);
        ex = experimentDao.existsById(10l);
        assertFalse(ex);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateExperiment() {
        Experiment a = experimentDao.getOne(3l);
        a.setDescription("some desc");
        Experiment a1 = experimentDao.save(a);

        assertEquals(a.getTitle(), a1.getTitle());
        assertEquals(a.getId(), a1.getId());
        assertEquals(a.getDescription(), a1.getDescription());
    }
}
