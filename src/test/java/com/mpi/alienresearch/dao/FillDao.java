package com.mpi.alienresearch.dao;

import java.time.LocalDate;
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
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Artifact;
import com.mpi.alienresearch.model.Coordinates;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.LandingPoint;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.Subject;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;
import com.mpi.alienresearch.model.enums.ExperimentStatus;
import com.mpi.alienresearch.model.enums.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FillDao {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private SubjectDao subjectDao;
    
    @Autowired
    private ArtifactDao artifactDao;
    
    @Autowired
    private ExperimentDao experimentDao;
    
    @Autowired
    private ReportDao reportDao;

    @Autowired
    private ApplicationDao applicationDao;
    
    @Autowired
    private LandingPointDao landingPointDao;
    
    @Test
    @Transactional
    @Rollback(true)
    public void fillUsers() {
        User u = new User();

        u.setPassword("admin");
        u.setUsername("admin");
        u.setRole(UserRole.ADMIN);
        u.setJobAgreementNumber("1000000");
        u.setFirstName("Admin");
        u.setLastName("Lord");
        u.setPhoneNumber("0-000-00-00");
        u.setBirthDate(LocalDate.of(1999, 6, 25));
        u = userDao.save(u);

        u = new User();
        u.setPassword("dir");
        u.setUsername("dir");
        u.setRole(UserRole.DIRECTOR);
        u.setJobAgreementNumber("1000001");
        u.setFirstName("Director");
        u.setLastName("Lead");
        u.setPhoneNumber("0-500-00-00");
        u.setBirthDate(LocalDate.of(1969, 4, 25));
        u = userDao.save(u);

        u = new User();
        u.setPassword("lander");
        u.setUsername("lander");
        u.setRole(UserRole.LANDER);
        u.setJobAgreementNumber("1000004");
        u.setFirstName("Lander");
        u.setLastName("Down");
        u.setPhoneNumber("0-000-00-01");
        u.setBirthDate(LocalDate.of(2003, 4, 15));
        u = userDao.save(u);

        u = new User();
        u.setPassword("ant");
        u.setUsername("ant");
        u.setRole(UserRole.ANALYTIC);
        u.setJobAgreementNumber("1000005");
        u.setFirstName("Analytic");
        u.setLastName("Look");
        u.setPhoneNumber("0-000-00-10");
        u.setBirthDate(LocalDate.of(1995, 11, 6));
        u = userDao.save(u);

        u = new User();
        u.setPassword("tech");
        u.setUsername("tech");
        u.setRole(UserRole.TECHNICIAN);
        u.setJobAgreementNumber("1000007");
        u.setFirstName("Technic");
        u.setLastName("Screw");
        u.setPhoneNumber("0-000-01-11");
        u.setBirthDate(LocalDate.of(1999, 8, 19));
        u = userDao.save(u);

        u = new User();
        u.setPassword("res1");
        u.setUsername("res1");
        u.setRole(UserRole.RESEARCHER);
        u.setJobAgreementNumber("1000012");
        u.setFirstName("Researcher");
        u.setLastName("First");
        u.setPhoneNumber("0-000-20-00");
        u.setBirthDate(LocalDate.of(1999, 6, 3));
        u = userDao.save(u);

        u = new User();
        u.setPassword("res2");
        u.setUsername("res2");
        u.setRole(UserRole.RESEARCHER);
        u.setJobAgreementNumber("1000013");
        u.setFirstName("Researcher");
        u.setLastName("Second");
        u.setPhoneNumber("0-000-30-00");
        u.setBirthDate(LocalDate.of(1995, 5, 25));
        u = userDao.save(u);

    }

    
    @Test
    @Transactional
    @Rollback(true)
    public void fillSubjects() {
        Subject u = new Subject();
        u.setBirthDate(LocalDate.of(1973, 4, 25));
        u.setEyesColor("bright");
        u.setHairColor("red");
        u.setHeight(1.5);
        u.setWeight(60.0);
        u.setName("Subs");
        u = subjectDao.save(u);

        u = new Subject();
        u.setBirthDate(LocalDate.of(1985, 8, 15));
        u.setEyesColor("blue");
        u.setHairColor("black");
        u.setHeight(1.7);
        u.setWeight(83.0);
        u.setName("Sir");
        u = subjectDao.save(u);

        u = new Subject();
        u.setBirthDate(LocalDate.of(1999, 6, 21));
        u.setSkinColor("yellow");
        u.setHairColor("brown");
        u.setHeight(1.95);
        u.setWeight(103.6);
        u.setName("Master");
        u = subjectDao.save(u);

    }


    @Test
    @Transactional
    @Rollback(true)
    public void fillArtifacts() {
        Artifact a = new Artifact();
        a.setName("Empty");
        a.setRadiation(10.0);
        a.setDescription("desc1");
        artifactDao.save(a);

        a = new Artifact();
        a.setName("Full");
        a.setRadiation(1.6);
        a.setDescription("desc2");
        artifactDao.save(a);

        a = new Artifact();
        a.setName("Bread");
        a.setRadiation(0.0);
        a.setDescription("desc3");
        artifactDao.save(a);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void fillExperiments() {
        Experiment a = new Experiment();
        a.setTitle("Exp1");
        a.setDescription("desc1");
        a.setResearchGroup("1");
        a.setCreationTime(LocalDateTime.of(2020, 4, 25, 0, 0, 0));
        a.setStatus(ExperimentStatus.CREATED);
        experimentDao.save(a);

        a = new Experiment();
        a.setTitle("Exp2");
        a.setDescription("desc2");
        a.setResearchGroup("1");
        a.setCreationTime(LocalDateTime.of(2020, 4, 26, 0, 0, 0));
        a.setStatus(ExperimentStatus.IN_PROGRESS);
        experimentDao.save(a);

        a = new Experiment();
        a.setTitle("Exp3");
        a.setDescription("desc3");
        a.setResearchGroup("2");
        a.setCreationTime(LocalDateTime.of(2020, 4, 27, 0, 0, 0));
        a.setStatus(ExperimentStatus.IN_PROGRESS);
        experimentDao.save(a);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void fillExperimentReports() {
        Experiment e2 = new Experiment();
        e2.setId(2l);
        Experiment e3 = new Experiment();
        e3.setId(3l);

        Report a = new Report();
        a.setTitle("Rep1");
        a.setContent("report1");
        a.setExperiment(e2);
        a.setCreationDate(LocalDateTime.of(2020, 4, 25, 10, 15, 0));
        reportDao.save(a);

        a = new Report();
        a.setTitle("Rep2");
        a.setContent("report2");
        a.setExperiment(e2);
        a.setCreationDate(LocalDateTime.of(2020, 4, 26, 10, 15, 0));
        reportDao.save(a);

        a = new Report();
        a.setTitle("Rep3");
        a.setContent("report3");
        a.setExperiment(e3);
        a.setCreationDate(LocalDateTime.of(2020, 4, 27, 10, 15, 0));
        reportDao.save(a);    
    }

    @Test
    @Transactional
    @Rollback(true)
    public void fillApplicationReports() {
        Application e2 = new AppAnalysis();
        e2.setId(6l);

        Report a = new Report();
        a.setTitle("RepA1");
        a.setContent("reportA1");
        a.setApplication(e2);
        a.setCreationDate(LocalDateTime.of(2020, 4, 25, 10, 15, 0));
        reportDao.save(a);
   
    }

    @Test
    @Transactional
    @Rollback(true)
    public void fillApplications() {
        Experiment e2 = new Experiment();
        e2.setId(2l);
        Experiment e3 = new Experiment();
        e3.setId(3l);
        

        // AppTechnic at = new AppTechnic();
        // at.setType(AppType.TECHNIC);
        // at.setDescription("tech_app_1");
        // at.setContent("content_tech_1");
        // at.setExperiment(e2);
        // at.setCreationDate(LocalDateTime.of(2020, 4, 25, 10, 15, 0));
        // at.setStatus(AppStatus.ACCEPTED);
        // at.setExecutionGroup("10");
        // applicationDao.save(at);

        AppTechnic at = new AppTechnic();
        at.setType(AppType.TECHNIC);
        at.setDescription("tech_app_2");
        at.setContent("content_tech_2");
        at.setExperiment(e2);
        at.setCreationDate(LocalDateTime.of(2020, 4, 25, 10, 15, 0));
        at.setStatus(AppStatus.APPROVED);
        applicationDao.save(at);

        // AppAnalysis aa = new AppAnalysis();
        // aa.setType(AppType.ANALYSIS);
        // aa.setDescription("anl_app_1");
        // aa.setExperiment(e2);
        // aa.setCreationDate(LocalDateTime.of(2020, 4, 26, 10, 15, 0));
        // aa.setStatus(AppStatus.CREATED);
        // aa.setAnalysisDescription("desc_analysis_1");
        // Subject s = new Subject();
        // s.setId(1l);
        // aa.setSubject(s);
        // applicationDao.save(aa);

        // AppLanding al = new AppLanding();

        // LandingPoint lp1 = new LandingPoint();
        // lp1.setAmount(1);
        // lp1.setApplication(al);
        // Artifact art = new Artifact();
        // art.setId(1l);
        // lp1.setArtifact(art);
        // lp1.setCoordinates(new Coordinates(2.5f, 0.0f));

        // LandingPoint lp2 = new LandingPoint();
        // lp2.setAmount(2);
        // lp2.setApplication(al);
        // art = new Artifact();
        // art.setId(2l);
        // lp2.setArtifact(art);
        // lp2.setCoordinates(new Coordinates(3.5f, 1.0f));

        // al.setType(AppType.LANDING);
        // al.setDescription("land_app_1");
        // al.setStatus(AppStatus.APPROVED);
        // al.setExperiment(e3);
        // al.setCreationDate(LocalDateTime.of(2020, 4, 27, 10, 15, 0));
        // List<LandingPoint> llp = new ArrayList<>();
        // llp.add(lp1);
        // llp.add(lp2);
        // al.setLandingPoints(llp);

        // applicationDao.save(al);   
        // landingPointDao.save(lp1);   
        // landingPointDao.save(lp2);  
    }
}
