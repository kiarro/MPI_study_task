package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.ApplicationRepository;
import com.mpi.alienresearch.dao.ApplicationRepository;
import com.mpi.alienresearch.filters.ApplicationFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.Decision;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

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
        return applicationRepository.save(application).getId();
    }

    @Override
    public void update(Long id, Application application) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setState(Long id, AppStatus status) {
        Application app = applicationRepository.findById(id.longValue());
        app.setStatus(status);
        applicationRepository.save(app);
    }

    @Override
    public List<Application> getByExperiment(Long experimentId) {
        return applicationRepository.findByExperimentId(experimentId);
    }

}
