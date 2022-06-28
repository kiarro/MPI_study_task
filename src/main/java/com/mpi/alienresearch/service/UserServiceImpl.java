package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mpi.alienresearch.dao.CredentialsDao;
import com.mpi.alienresearch.dao.UserDao;
import com.mpi.alienresearch.dao.UserGroupDao;
import com.mpi.alienresearch.model.Credentials;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.model.UserGroup;
import com.mpi.alienresearch.state.PersonalInfo;
import com.mpi.alienresearch.state.State;

@Service
public class UserServiceImpl implements UserService {

    final UserDao userDao;
    final UserGroupDao userGroupDao;
    final CredentialsDao credentialsDao;

    public UserServiceImpl(UserDao userDao,
            UserGroupDao userGroupDao,
            CredentialsDao credentialsDao) {
        this.userDao = userDao;
        this.userGroupDao = userGroupDao;
        this.credentialsDao = credentialsDao;
    }

    @Override
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

    @Override
    public long add(User user) {
        // save user
        user = userDao.save(user);
        // create credentials
        user.setCredentials(new Credentials(null, null, user));
        String username = user.getLastName().toLowerCase();
        Integer i = 1;
        while (credentialsDao.findByUsername(username).isPresent()) {
            username = username + i.toString();
            i++;
        }
        user.getCredentials().setUsername(username);
        user.getCredentials().setPassword(username);
        // save credentials
        credentialsDao.save(user.getCredentials());
        // возвращаем id созданного пользователя
        return user.getId();
    }

    @Override
    public void update(long id, User user) {
        user.setId(id);
        userDao.save(user);
    }

    @Override
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

    @Override
    public User login(String username, String password) {
        Optional<Credentials> credentials = credentialsDao.findByUsername(username);
        if (credentials.isPresent()) {
            if (credentials.get().getPassword().equals(password)) {
                State.setCurrentUser(credentials.get().getUser());
                return credentials.get().getUser();
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        "Wrong password");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "User with such username don't exists");
        }
    }

    @Override
    public void logout() {
        State.setCurrentUser(null);
    }

    /**
     * Just return all
     */
    @Override
    public List<User> getPage(Long offset, Long limit, String[] sortvalues) {
        return userDao.findAll();
    }

    @Override
    public void setPassword(String password){
        Credentials c = credentialsDao.findById(State.getCurrentUser().getId()).get();
        c.setPassword(password);
        credentialsDao.save(c);
    }
}
