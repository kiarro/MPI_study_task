package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.ApplicationDao;
import com.mpi.alienresearch.dao.ApplicationDao;
import com.mpi.alienresearch.filters.ApplicationFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.Decision;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    final ApplicationDao applicationRepository;

    public ApplicationServiceImpl(ApplicationDao applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application get(Long id) {
        return applicationRepository.findById(id).get();
    }

    /**
     * Just returns all
     */
    @Override
    public <T extends Application> List<T> getPage(Long offset, Long limit, String[] sortvalues, Application filter) {
        Example<Application> example = Example.of(filter); 
        return (List<T>)(List<?>)applicationRepository.findAll(example);
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
    public void setStatus(Long id, AppStatus status) {
        Application app = applicationRepository.findById(id).get();
        app.setStatus(status);
        applicationRepository.save(app);
    }

    @Override
    public List<Application> getByExperiment(Long experimentId) {
        // return applicationRepository.findByExperimentId(experimentId);
        return null;
    }

    @Override
    public void setExecutionGroup(Long id, User user) {
        Application app = applicationRepository.findById(id).get();
        app.setExecutionGroup(user.getUserGroup());
        applicationRepository.save(app);
    }

}
