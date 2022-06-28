package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.state.PersonalInfo;

public interface UserService {
    public User get(long id);
    public List<User> getPage(Long offset, Long limit, String[] sortvalues);
    public long add(User report);
    public void update(long id, User report);
    public void updateGroup(long id, Optional<Long> group);

    public User login(String username, String password);
    public void logout();

    public void setPassword(String password);
}
