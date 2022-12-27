// package com.mpi.alienresearch;

// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import java.util.logging.Logger;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.mpi.alienresearch.dao.*;
// import com.mpi.alienresearch.model.*;
// import com.mpi.alienresearch.service.UserService;

// @SpringBootTest
// public class JacksonTests {
    
//     private static Logger _log = Logger.getLogger(JpatestApplicationTests.class.getName());

// 	@Autowired
// 	private ApplicationDao applicationDao;
// 	@Autowired
// 	private ArtifactDao artifactDao;
// 	@Autowired
// 	private CredentialsDao credentialsDao;
// 	@Autowired
// 	private ExperimentDao experimentDao;
// 	@Autowired
// 	private LandingPointDao landingPointDao;
// 	@Autowired
// 	private ReportDao reportDao;
// 	@Autowired
// 	private SubjectDao subjectDao;
// 	@Autowired
// 	private UserDao userDao;
// 	@Autowired
// 	private UserGroupDao userGroupDao;

//     @Autowired
//     private UserService userService;

// 	@Test
// 	void contextLoads() {
// 		assertNotNull(applicationDao);
// 		assertNotNull(artifactDao);
// 		assertNotNull(credentialsDao);
// 		assertNotNull(experimentDao);
// 		assertNotNull(landingPointDao);
// 		assertNotNull(reportDao);
// 		assertNotNull(subjectDao);
// 		assertNotNull(userDao);
// 		assertNotNull(userGroupDao);

// 		assertNotNull(userService);
// 	}

//     @Test
// 	void serializeUser() throws JsonProcessingException {
// 		User u = userService.login("admin", "admin");
//         // String json = new ObjectMapper().writeValueAsString(u);
// 		String json = "00000";
//         _log.info(json);
// 	}
// }
