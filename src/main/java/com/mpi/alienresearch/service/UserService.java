package com.mpi.alienresearch.service;

import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.state.PersonalInfo;

public interface UserService {
    public User get(long id);
    // public Collection<Report> getPage(Long offset, Long limit, String[] sortvalues, ReportFilter filter);
    public long add(User report);
    public void update(long id, User report);
    public void updateCurrentInfo(PersonalInfo info);

    public User login(String username, String password);
    public void logout();
}
