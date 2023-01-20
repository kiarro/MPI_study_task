package com.mpi.alienresearch.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mpi.alienresearch.model.User;
// import com.mpi.alienresearch.model.UserGroup;
import com.mpi.alienresearch.model.enums.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    // @Autowired
    // private UserGroupDao userGroupDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testFindById() {
        User u = userDao.getOne(3l);

        assertEquals(u.getId().longValue(), 3l);
        assertEquals(u.getLastName(), "Ant");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindByUsername() {
        Optional<User> ou = userDao.findByUsername("dir1");
        assertTrue(ou.isPresent());

        User u = ou.get();
        assertEquals(u.getRole(), UserRole.DIRECTOR);
        assertEquals(u.getId().longValue(), 4l);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddUser() {
        User u = new User();
        u.setPassword("test1");
        u.setUsername("test1");
        u.setRole(UserRole.LANDER);
        u.setJobAgreementNumber("1010008");
        u.setFirstName("Tes");
        u.setLastName("Tos");
        u.setPhoneNumber("0-100-00-08");

        u = userDao.save(u);
        assertNotNull(u.getId());
        assertEquals(u.getRole(), UserRole.LANDER);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateUser() {
        User u = userDao.getOne(3l);
        u.setAboutYourself("some info");

        User u1 = userDao.save(u);
        assertNotNull(u1);
        assertEquals(u1.getRole(), u.getRole());
        assertEquals(u1.getAboutYourself(), u.getAboutYourself());
    }

    // @Test
    // @Transactional
    // @Rollback(true)
    // public void testAddUserGroup() {
    //     UserGroup ug = new UserGroup();
    //     ug.setDescription("group new");
    //     ug.setId(5l);

    //     ug = userGroupDao.save(ug);
    //     assertNotNull(ug.getId());
    // }
}
