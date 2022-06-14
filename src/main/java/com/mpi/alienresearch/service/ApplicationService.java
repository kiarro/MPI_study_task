package com.mpi.alienresearch.service;

import java.util.Collection;

import com.mpi.alienresearch.filters.ApplicationFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.enums.Decision;

public interface ApplicationService {
    public Application get(String id);
    public Collection<Application> getPage(Long offset, Long limit, String[] sortvalues, ApplicationFilter filter);
    public String add(Application application);
    public void update(String id, Application application);

    public void setDecision(String id, Decision decision);
    public void setAccepted(String id, Boolean decision);

    public void setDone(String id, Report report);
}
