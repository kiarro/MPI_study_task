package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.List;

import com.mpi.alienresearch.filters.ApplicationFilter;
import com.mpi.alienresearch.model.AppTechnic;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.Decision;

public interface ApplicationService {
    public Application get(Long id);
    public <T extends Application> List<T> getPage(Long offset, Long limit, String[] sortvalues, Application filter);
    public Long add(Application application);
    public void update(Long id, Application application);

    public void setStatus(Long id, AppStatus status);

    public Collection<Application> getByExperiment(Long experimentId);
}
