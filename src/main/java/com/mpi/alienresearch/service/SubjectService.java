package com.mpi.alienresearch.service;

import java.util.Collection;

import com.mpi.alienresearch.model.Subject;

public interface SubjectService {
    public Subject get(Long id);
    public boolean exists(Long id);
    public Collection<Subject> getPage(Long offset, Long limit, String[] sortvalues);
    public Long add(Subject report);
    public void update(Long id, Subject report);

}
