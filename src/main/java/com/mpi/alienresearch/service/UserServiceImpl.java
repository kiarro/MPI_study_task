package com.mpi.alienresearch.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.mpi.alienresearch.dao.UserRepository;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.state.PersonalInfo;
import com.mpi.alienresearch.state.State;

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;

    @Override
    public User get(long id) {
        return repository.findById(id);
    }

    @Override
    public long add(User user) {
        user = repository.save(user);
        return user.getId();
    }

    @Override
    public void update(long id, User user) {
        User db_user = repository.getReferenceById(id);
        db_user.updateWith(user);
        repository.save(db_user);
    }

    @Override
    public void updateCurrentInfo(PersonalInfo info) {
        User db_user = repository.getReferenceById(State.getCurrentUser().getId());
        db_user.setPhone(info.getPhone_number());
        db_user.setEmail(info.getEmail());
        db_user.setAboutYourself(info.getAbout());
        repository.save(db_user);
    }

    @Override
    public User login(String username, String password) {
        Optional<User> user = repository.findByCredentials(username, password);
        if (user.isPresent()){
            State.setCurrentUser(user.get());
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public void logout() {
        State.setCurrentUser(null);
    }
    
}
