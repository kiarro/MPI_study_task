package com.mpi.alienresearch.service;

import java.util.Collection;

import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.state.PersonalInfo;

public interface UserService {
    public User get(long id);
    public Collection<User> getPage(Long offset, Long limit, String[] sortvalues);
    public long add(User report);
    public void update(long id, User report);
    public void updateCurrentInfo(PersonalInfo info);

    public User login(String username, String password);
    public void logout();
}
