package com.mpi.alienresearch.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mpi.alienresearch.DemoApplication;
import com.mpi.alienresearch.model.Artifact;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArtifactDaoTest {
    @Autowired
    private ArtifactDao artifactDao;
    
    @Test
    @Transactional
    @Rollback(true)
    public void testAddArtifact() {
        Artifact a = new Artifact();
        a.setDescription("desc");
        a.setName("test1");
        a.setRadiation(10.02);

        Artifact a1 = artifactDao.save(a);

        assertEquals(a1.getId(), a.getId());
        assertEquals(a1.getDescription(), a.getDescription());
        assertEquals(a1.getName(), a.getName());
        assertEquals(a1.getRadiation(), a.getRadiation());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAll() {
        List<Artifact> artifacts = artifactDao.findAll();
        assertEquals(artifacts.size(), 2);

        assertEquals(artifacts.get(0).getId().longValue(), 2l);
        assertEquals(artifacts.get(1).getId().longValue(), 1l);
        assertEquals(artifacts.get(1).getName(), "t1");
        assertEquals(artifacts.get(0).getRadiation().doubleValue(), 10.02, 0.001);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetArtifact() {
        Artifact a = artifactDao.getOne(1l);
        assertNotNull(a);
        assertEquals(a.getName(), "t1");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetExists() {
        boolean ex = artifactDao.existsById(2l);
        assertTrue(ex);
        ex = artifactDao.existsById(10l);
        assertFalse(ex);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateArtifact() {
        Artifact a = artifactDao.getOne(1l);
        a.setName("new name");
        a.setRadiation(20.0);
        artifactDao.save(a);

        a = artifactDao.getOne(1l);
        assertEquals(a.getName(), "new name");
        assertEquals(a.getDescription(), "desc1");
        assertEquals(a.getRadiation().doubleValue(), 20.0, 0.001);
    }
}
