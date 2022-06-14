package com.mpi.alienresearch.service;

import java.util.Collection;

import com.mpi.alienresearch.filters.ExperimentFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;

public interface ExperimentService {
    public Experiment get(String id);
    public Collection<Experiment> getPage(Long offset, Long limit, String[] sortvalues, ExperimentFilter filter);
    public String add(Experiment experiment);
    public void update(String id, Experiment experiment);

    public void madeArchive(String id);
    public String addApplication(Application app);
    public String addReport(Report report);
    
    public Collection<Experiment> getPage();

}
