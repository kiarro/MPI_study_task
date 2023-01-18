package com.mpi.alienresearch.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mpi.alienresearch.model.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SubjectDaoTest {
    @Autowired
    private SubjectDao subjectDao;
    
    @Test
    @Transactional
    @Rollback(true)
    public void testAddSubject() {
        Subject a = new Subject();
        a.setWeight(10.2);
        a.setName("test1");
        a.setSkinColor("yellow");

        Subject a1 = subjectDao.save(a);

        assertEquals(a1.getId(), a.getId());
        assertEquals(a1.getWeight().doubleValue(), a.getWeight().doubleValue(), 0.001);
        assertEquals(a1.getName(), a.getName());
        assertEquals(a1.getSkinColor(), a.getSkinColor());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAll() {
        List<Subject> subjects = subjectDao.findAll();
        assertEquals(subjects.size(), 2);

        assertEquals(subjects.get(0).getId().longValue(), 1l);
        assertEquals(subjects.get(1).getId().longValue(), 2l);
        assertEquals(subjects.get(1).getName(), "Rob");
        assertEquals(subjects.get(0).getWeight().doubleValue(), 25.3, 0.001);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetSubject() {
        Subject a = subjectDao.getOne(1l);
        assertNotNull(a);
        assertEquals(a.getName(), "John");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetExists() {
        boolean ex = subjectDao.existsById(2l);
        assertTrue(ex);
        ex = subjectDao.existsById(10l);
        assertFalse(ex);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateSubject() {
        Subject a = subjectDao.getOne(1l);
        a.setName("new name");
        a.setSpecials("scar");
        subjectDao.save(a);

        a = subjectDao.getOne(1l);
        assertEquals(a.getName(), "new name");
        assertEquals(a.getHairColor(), "red");
        assertEquals(a.getSpecials(), "scar");
    }
}
