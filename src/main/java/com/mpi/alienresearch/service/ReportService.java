package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.List;

import com.mpi.alienresearch.filters.ReportFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;

public interface ReportService {
    public Report get(Long id);
    public Collection<Report> getPage(Long offset, Long limit, String[] sortvalues, ReportFilter filter);
    public Long add(Report report);
    public void update(Long id, Report report);

    public List<Report> getByExperiment(Experiment experiment);
    public Report getByApplication(Application application);

}
