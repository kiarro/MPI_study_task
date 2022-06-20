package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mpi.alienresearch.dao.ApplicationRepository;
import com.mpi.alienresearch.dao.ApplicationRepository;
import com.mpi.alienresearch.filters.ApplicationFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.enums.Decision;

public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public Application get(Long id) {
        Application app = applicationRepository.findById(id.longValue());
        
        return app;
    }

    /**
     * Just returns all
     */
    @Override
    public <T extends Application> List<T> getPage(Long offset, Long limit, String[] sortvalues, ApplicationFilter filter) {
        return (List<T>)(List<?>)applicationRepository.findAll();
    }

    @Override
    public Long add(Application application) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Long id, Application application) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setDecision(Long id, Decision decision) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setAccepted(Long id, Boolean decision) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setDone(Long id, Report report) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<Application> getByExperiment(Long experimentId) {
        // TODO Auto-generated method stub
        return null;
    }

}
