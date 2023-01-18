package com.mpi.alienresearch.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mpi.alienresearch.model.AppAnalysis;
import com.mpi.alienresearch.model.AppLanding;
import com.mpi.alienresearch.model.AppTechnic;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.LandingPoint;
import com.mpi.alienresearch.model.Subject;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;

public class ApplicationDaoTest {
    @Autowired
    private ApplicationDao applicationDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testFindByExperiment() {

    }

    @Test
    // @Transactional
    // @Rollback(true)
    public void testAddAppTech() {
        User u = new User(); u.setId(6l);
        Experiment e = new Experiment(); e.setId(2l);

        AppTechnic at = new AppTechnic();
        at.setCreator(u);
        at.setCreationDate(LocalDateTime.now());
        at.setContent("need to do this");
        at.setDescription("desc1");
        at.setExperiment(e);
        at.setLastStatusTransitionDate(LocalDateTime.now());
        at.setType(AppType.TECHNIC);
        at.setStatus(AppStatus.CREATED);
        applicationDao.save(at);
        
        AppAnalysis aa = new AppAnalysis();
        aa.setCreator(u);
        aa.setCreationDate(LocalDateTime.now());
        aa.setDescription("desc2");
        aa.setExperiment(e);
        aa.setLastStatusTransitionDate(LocalDateTime.now());
        aa.setType(AppType.ANALYSIS);
        aa.setStatus(AppStatus.CLOSED);
        Subject s = new Subject(); s.setId(1l);
        aa.setSubject(s);
        applicationDao.save(at);

        AppLanding al = new AppLanding();
        al.setCreator(u);
        al.setCreationDate(LocalDateTime.now());
        al.setDescription("desc2");
        al.setExperiment(e);
        al.setLastStatusTransitionDate(LocalDateTime.now());
        al.setType(AppType.LANDING);
        al.setStatus(AppStatus.APPROVED);
        applicationDao.save(al);

        List<LandingPoint> listlp = new ArrayList<>();
        LandingPoint lp = new LandingPoint();
        lp.setAmount(1);
        lp.setApplication(al);
        // listlp.add(lp)
        
    }
}
