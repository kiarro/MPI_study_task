package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.List;

import com.mpi.alienresearch.filters.ExperimentFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.UserGroup;
import com.mpi.alienresearch.model.enums.ExperimentStatus;

public interface ExperimentService {
    public Experiment get(long id);
    public Collection<Experiment> getPage(Long offset, Long limit, String[] sortvalues, Experiment filter);

    public List<Experiment> getByGroup(UserGroup group);

    public Long add(Experiment experiment);
    public void update(long id, Experiment experiment);

    public Long addApplication(long id, Application app);
    public Long addReport(long id, Report report);

    public void setStatus(long id, ExperimentStatus state);
    

}
