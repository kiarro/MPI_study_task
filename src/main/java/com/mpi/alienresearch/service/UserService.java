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

import com.mpi.alienresearch.controllers.WebSocketController;
import com.mpi.alienresearch.dao.UserDao;
import com.mpi.alienresearch.model.User;

@Service
public class UserService implements UserDetailsService {

    final UserDao userDao;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    WebSocketController webSocketController;
    
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

        user.setPassword(userFromDB.get().getPassword());
        // user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);

        webSocketController.sendUser("Your user was updated", user.getId().toString());
        return true;
    }

    
    public void updateGroup(long id, Optional<String> group) {
        User u = this.get(id);
        // if group is not null
        u.setUserGroup(group.get());
        userDao.save(u);

        webSocketController.sendUser("Your user was updated", String.valueOf(id));
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
