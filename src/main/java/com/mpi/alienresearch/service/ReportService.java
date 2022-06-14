package com.mpi.alienresearch.service;

import java.util.Collection;

import com.mpi.alienresearch.filters.ReportFilter;
import com.mpi.alienresearch.model.Report;

public interface ReportService {
    public Report get(String id);
    public Collection<Report> getPage(Long offset, Long limit, String[] sortvalues, ReportFilter filter);
    public String add(Report report);
    public void update(String id, Report report);
}
