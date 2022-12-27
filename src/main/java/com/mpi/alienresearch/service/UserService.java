package com.mpi.alienresearch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mpi.alienresearch.dao.UserDao;
import com.mpi.alienresearch.dao.UserGroupDao;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.model.UserGroup;

@Service
public class UserService implements UserDetailsService {

    final UserDao userDao;
    final UserGroupDao userGroupDao;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDao userDao,
            UserGroupDao userGroupDao) {
        this.userDao = userDao;
        this.userGroupDao = userGroupDao;
    }

    
    public User get(long id) {
        Optional<User> user = userDao.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("User with id=%d don't exists", id));
        }
    }
    
    public boolean saveUser(User user) {
        Optional<User> userFromDB = userDao.findByUsername(user.getUsername());

        if (userFromDB.isPresent()) {
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    public boolean updateUser(User user) {
        Optional<User> userFromDB = userDao.findByUsername(user.getUsername());

        if (userFromDB.isEmpty()) {
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    
    public void updateGroup(long id, Optional<Long> group) {
        User u = this.get(id);
        // if group is not null
        if (group.isPresent()) { // set it to user
            Optional<UserGroup> ug = userGroupDao.findById(group.get());
            // if user group exists
            if (ug.isPresent()) {
                // set it to user
                u.setUserGroup(ug.get());
            } else {
                // create user group with id and set it to user
                u.setUserGroup(
                        userGroupDao.save(new UserGroup(group.get())));
            }
        } else { // set null group to user
            u.setUserGroup(null);
        }
        userDao.save(u);
    }
    
    public List<User> getPage(Long offset, Long limit, String[] sortvalues) {
        return userDao.findAll();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.get();
    }

    public boolean deleteUser(Long userId) {
        if (userDao.findById(userId).isPresent()) {
            userDao.deleteById(userId);
            return true;
        }
        return false;
    }
}
