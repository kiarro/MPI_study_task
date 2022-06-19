package com.mpi.alienresearch.service;

import java.util.Collection;

import com.mpi.alienresearch.filters.ExperimentFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;

public interface ExperimentService {
    public Experiment get(long id);
    public Collection<Experiment> getPage(Long offset, Long limit, String[] sortvalues, ExperimentFilter filter);
    public Long add(Experiment experiment);
    public void update(long id, Experiment experiment);

    public void madeArchive(String id);
    public Long addApplication(Application app);
    public Long addReport(Report report);
    
    public Collection<Experiment> getPage();

}
