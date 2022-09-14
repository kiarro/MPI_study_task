package com.mpi.alienresearch;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mpi.alienresearch.dao.*;
import com.mpi.alienresearch.model.*;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.ExperimentStatus;
import com.mpi.alienresearch.model.enums.UserRole;

@SpringBootTest
class JpatestApplicationTests {

	private static Logger _log = Logger.getLogger(JpatestApplicationTests.class.getName());

	@Autowired
	private ApplicationDao applicationDao;
	@Autowired
	private ArtifactDao artifactDao;
	@Autowired
	private CredentialsDao credentialsDao;
	@Autowired
	private ExperimentDao experimentDao;
	@Autowired
	private LandingPointDao landingPointDao;
	@Autowired
	private ReportDao reportDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserGroupDao userGroupDao;

	@Test
	void contextLoads() {
		assertNotNull(applicationDao);
		assertNotNull(artifactDao);
		assertNotNull(credentialsDao);
		assertNotNull(experimentDao);
		assertNotNull(landingPointDao);
		assertNotNull(reportDao);
		assertNotNull(subjectDao);
		assertNotNull(userDao);
		assertNotNull(userGroupDao);
	}

	@Test
	void testUsersPostGetUpdate() {
		Credentials credentials = oCredentials;
		User user = oUser1;

		// save group
		UserGroup sGroup = userGroupDao.save(user.getUserGroup());
		user.setUserGroup(sGroup);

		// save user
		Long count1 = userDao.count();
		User sUser = userDao.save(user);
		credentials.setUser(sUser);
		sUser.setCredentials(credentials);
		Long count2 = userDao.count();

		assertTrue(count2 - count1 == 1);

		Long sId = sUser.getId();

		// save credentials
		Credentials sCredentials = credentialsDao.save(credentials);
		assertEquals(credentials.getPassword(), sCredentials.getPassword());
		assertEquals(credentials.getUsername(), sCredentials.getUsername());

		// get update user
		sUser = userDao.findById(sId).get();

		assertEquals(user.getAboutYourself(), sUser.getAboutYourself());
		assertEquals(user.getBirthDate(), sUser.getBirthDate());
		assertEquals(user.getFirstName(), sUser.getFirstName());
		assertEquals(user.getEmail(), sUser.getEmail());

		String newFirstName = "newFirstName1";

		sUser.setFirstName(newFirstName);
		userDao.save(sUser);
		sUser = userDao.findById(sId).get();

		assertEquals(user.getAboutYourself(), sUser.getAboutYourself());
		assertEquals(user.getBirthDate(), sUser.getBirthDate());
		assertEquals(newFirstName, sUser.getFirstName());
		assertEquals(user.getEmail(), sUser.getEmail());

	}

	@Test
	void testSubjectPostGetUpdate() {
		Subject subject = oSubject;

		// save
		Long count1 = subjectDao.count();
		Subject sSubject = subjectDao.save(subject);
		Long count2 = subjectDao.count();

		assertTrue(count2 - count1 == 1);

		Long sId = sSubject.getId();

		// update
		String newHair = "new hair color";
		sSubject.setHairColor(newHair);
		subjectDao.save(sSubject);

		// get
		sSubject = subjectDao.findById(sId).get();
		assertEquals(newHair, sSubject.getHairColor());
		assertEquals(subject.getBirthDate(), sSubject.getBirthDate());
		assertEquals(subject.getName(), sSubject.getName());

	}

	@Test
	void testArtifactPostGetUpdate() {
		Artifact artifact = oArtifact;

		// save
		Long count1 = artifactDao.count();
		Artifact sArtifact = artifactDao.save(artifact);
		Long count2 = artifactDao.count();

		assertTrue(count2 - count1 == 1);

		Long sId = sArtifact.getId();

		// update
		String newDesc = "new art desc";
		sArtifact.setDescription(newDesc);
		artifactDao.save(sArtifact);

		// get
		sArtifact = artifactDao.findById(sId).get();
		assertEquals(newDesc, sArtifact.getDescription());
		assertEquals(artifact.getName(), sArtifact.getName());

	}

	@Test
	void testExperimentPostGetUpdate() {
		Experiment experiment = oExperiment;
		experiment.setResearchGroup(userGroupDao.findById(3l).get());

		// save
		Long count1 = experimentDao.count();
		Experiment sExperiment = experimentDao.save(experiment);
		Long count2 = experimentDao.count();

		assertTrue(count2 - count1 == 1);

		Long sId = sExperiment.getId();

		// update
		ExperimentStatus newStatus = ExperimentStatus.IN_PROGRESS;
		sExperiment.setStatus(newStatus);
		experimentDao.save(sExperiment);

		// get
		sExperiment = experimentDao.findById(sId).get();
		assertEquals(newStatus, sExperiment.getStatus());
		assertEquals(experiment.getResearchGroup().getId(), sExperiment.getResearchGroup().getId());
		assertEquals(experiment.getCreationTime(), sExperiment.getCreationTime());
	}

	@Test
	void testAppTechPostGetUpdate() {
		AppTechnic app = oAppTechnic;
		app.setExperiment(experimentDao.findById(1l).get());
		app.setCreator(userDao.findById(5l).get());

		// save
		Long count1 = applicationDao.count();
		AppTechnic sApp = applicationDao.save(app);
		Long count2 = applicationDao.count();

		assertTrue(count2 - count1 == 1);

		Long sId = sApp.getId();

		// update
		UserGroup newExecGroup = userGroupDao.findById(6l).get();
		AppStatus newStatus = AppStatus.ACCEPTED;
		sApp.setExecutionGroup(newExecGroup);
		sApp.setStatus(newStatus);
		applicationDao.save(sApp);

		// get
		sApp = applicationDao.findById(sId).get().asClass();
		assertEquals(newStatus, sApp.getStatus());
		assertEquals(newExecGroup.getId(), sApp.getExecutionGroup().getId());
		assertEquals(app.getContent(), sApp.getContent());
	}

	@Test
	void testAppAnalysisPostGetUpdate() {
		AppAnalysis app = oAppAnalysis;
		app.setExperiment(experimentDao.findById(1l).get());
		app.setCreator(userDao.findById(7l).get());
		app.setSubject(subjectDao.findById(1l).get());

		// save
		Long count1 = applicationDao.count();
		AppAnalysis sApp = applicationDao.save(app);
		Long count2 = applicationDao.count();

		assertTrue(count2 - count1 == 1);

		Long sId = sApp.getId();

		// update
		UserGroup newExecGroup = userGroupDao.findById(5l).get();
		AppStatus newStatus = AppStatus.APPROVED;
		String newDesc = "new desc of analysis";
		sApp.setExecutionGroup(newExecGroup);
		sApp.setStatus(newStatus);
		sApp.setAnalysisDescription(newDesc);
		applicationDao.save(sApp);

		// get
		sApp = applicationDao.findById(sId).get().asClass();
		assertEquals(newStatus, sApp.getStatus());
		assertEquals(newExecGroup.getId(), sApp.getExecutionGroup().getId());
		assertEquals(newDesc, sApp.getAnalysisDescription());
		assertEquals(app.getDescription(), sApp.getDescription());
	}

	@Test
	void testReportEPostGetUpdate() {
		// FOR EXPERIMENT
		Report report = oReport;
		report.setExperiment(experimentDao.findById(1l).get());

		// save
		Long count1 = reportDao.count();
		Report sReport = reportDao.save(report);
		Long count2 = reportDao.count();

		assertTrue(count2 - count1 == 1);

		Long sId = sReport.getId();

		// update
		String newContent = "new report content";
		sReport.setContent(newContent);
		reportDao.save(sReport);

		// get
		sReport = reportDao.findById(sId).get();
		assertEquals(newContent, sReport.getContent());
		assertEquals(report.getCreationDate(), sReport.getCreationDate());

	}

	@Test
	void testReportAPostGetUpdate() {
		// FOR APPLICATION
		Report report = oReport;
		report.setApplication(applicationDao.findById(1l).get());

		// save
		Long count1 = reportDao.count();
		Report sReport = reportDao.save(report);
		Long count2 = reportDao.count();

		assertTrue(count2 - count1 == 1);

		Long sId = sReport.getId();

		// update
		String newContent = "new report content";
		sReport.setContent(newContent);
		reportDao.save(sReport);

		// get
		sReport = reportDao.findById(sId).get();
		assertEquals(newContent, sReport.getContent());
		assertEquals(report.getCreationDate(), sReport.getCreationDate());
	}

	@Test
	void testAppLandingPostGetUpdate() {
		AppLanding app = oAppLanding;
		app.setExperiment(experimentDao.findById(1l).get());
		app.setCreator(userDao.findById(8l).get());

		// save app
		Long count1 = applicationDao.count();
		AppLanding sApp = applicationDao.save(app);
		Long count2 = applicationDao.count();

		assertTrue(count2 - count1 == 1);

		Long sId = sApp.getId();

		// save points
		LandingPoint lp1 = oLandingPoint1;
		lp1.setApplication(sApp);
		lp1.setArtifact(artifactDao.findById(1l).get());
		
		LandingPoint lp2 = oLandingPoint2;
		lp2.setApplication(sApp);
		lp2.setArtifact(artifactDao.findById(3l).get());

		landingPointDao.save(lp1);
		landingPointDao.save(lp2);

		// update
		UserGroup newExecGroup = userGroupDao.findById(5l).get();
		AppStatus newStatus = AppStatus.APPROVED;
		String newDesc = "new desc of analysis";
		sApp.setExecutionGroup(newExecGroup);
		sApp.setStatus(newStatus);
		sApp.setDescription(newDesc);
		applicationDao.save(sApp);

		// get
		sApp = applicationDao.findById(sId).get().asClass();
		assertEquals(newStatus, sApp.getStatus());
		assertEquals(newExecGroup.getId(), sApp.getExecutionGroup().getId());
		assertEquals(newDesc, sApp.getDescription());

		List<LandingPoint> lps = landingPointDao.findByApplication_Id(sId);
		assertEquals(lps.size(), 2);
		LandingPoint glp1 = lps.stream()
				.filter(p -> p.getArtifact().getId() == 1)
				.collect(Collectors.toList()).get(0);
		assertEquals(glp1.getAmount(), 1);
		assertEquals(glp1.getCoordinates().getX(), 0.2f);
		
	}

	User oUser1 = new User(0l, "firstName1", "lastName1",
			LocalDate.of(2000, 10, 25),
			"jobAgreementNumber1", UserRole.RESEARCHER,
			"phoneNumber1", "email1", "aboutYourself1",
			new UserGroup(0l));
	Credentials oCredentials = new Credentials("login1", "password1", oUser1);

	Subject oSubject = new Subject(0l, "subj name", "hairColor",
			"eyesColor", "skinColor", "specials",
			25.33, 100.25,
			LocalDate.of(1990, 03, 17));

	Artifact oArtifact = new Artifact(0l, " art name", " art description");

	Experiment oExperiment = new Experiment(0l, "title", "exp description",
			LocalDateTime.now(), null, ExperimentStatus.CREATED);

	// AppTechnic oAppTechnic = new AppTechnic(0l, "description",
	// 		null, null, LocalDateTime.now(),
	// 		AppStatus.CREATED, null, "content");

	// AppAnalysis oAppAnalysis = new AppAnalysis(0l,
	// 		"analysis description", null, null,
	// 		LocalDateTime.now(), AppStatus.CREATED, null,
	// 		null, "description2");

	Report oReport = new Report(0l, "report title",
			"content report", LocalDateTime.now(),
			null, null);

	LandingPoint oLandingPoint1 = new LandingPoint(0l,
			new Coordinates(0.2f, 3.6f),
			null, null, 1);
	LandingPoint oLandingPoint2 = new LandingPoint(0l,
			new Coordinates(1.2f, 3.7f),
			null, null, 3);
	AppLanding oAppLanding = new AppLanding(0l,
			"landing description", 
			null, null, LocalDateTime.now(),
			AppStatus.CREATED, null);
	
}
