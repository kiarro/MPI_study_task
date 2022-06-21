package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mpi.alienresearch.dao.UserRepository;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.state.PersonalInfo;
import com.mpi.alienresearch.state.State;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository repository){
        userRepository = repository;
    }

    @Override
    public User get(long id) {
        return userRepository.findById(id);
    }

    @Override
    public long add(User user) {
        user.setUsername(user.getLastName().toLowerCase());
        user.setPassword(user.getUsername());
        user = userRepository.save(user);
        return user.getId();
    }

    @Override
    public void update(long id, User user) {
        User db_user = userRepository.getReferenceById(id);
        db_user.updateWith(user);
        userRepository.save(db_user);
    }

    @Override
    public void updateCurrentInfo(PersonalInfo info) {
        User db_user = userRepository.getReferenceById(State.getCurrentUser().getId());
        db_user.setPhone(info.getPhone_number());
        db_user.setEmail(info.getEmail());
        db_user.setAboutYourself(info.getAbout());
        userRepository.save(db_user);
    }

    @Override
    public User login(String username, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByCredentials(username, password));
        if (user.isPresent()) {
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

    /**
     * Just return all
     */
    @Override
    public Collection<User> getPage(Long offset, Long limit, String[] sortvalues) {
        return userRepository.findAll();
    }

}
