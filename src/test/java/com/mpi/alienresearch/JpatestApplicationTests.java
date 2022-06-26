package com.mpi.alienresearch;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mpi.alienresearch.dao.*;
import com.mpi.alienresearch.model.*;
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

		assertTrue(count2-count1==1);

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

	User oUser1 = new User(0l, "firstName1", "lastName1",
			LocalDate.of(2000, 10, 25),
			"jobAgreementNumber1", UserRole.RESEARCHER,
			"phoneNumber1", "email1", "aboutYourself1",
			new UserGroup(0l));
	Credentials oCredentials = new Credentials("login1", "password1", oUser1);
}
