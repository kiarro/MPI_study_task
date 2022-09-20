package com.mpi.alienresearch.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.ApplicationDao;
import com.mpi.alienresearch.dao.ReportDao;
import com.mpi.alienresearch.dao.ApplicationDao;
import com.mpi.alienresearch.filters.ApplicationFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.Decision;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    final ApplicationDao applicationDao;
    final ReportDao reportDao;

    public ApplicationServiceImpl(ApplicationDao applicationRepository, ReportDao reportRepository) {
        this.applicationDao = applicationRepository;
        this.reportDao = reportRepository;
    }

    @Override
    public Application get(Long id) {
        return applicationDao.findById(id).get();
    }

    /**
     * Just returns all
     */
    @Override
    public <T extends Application> List<T> getPage(Long offset, Long limit, String[] sortvalues, Application filter) {
        Example<Application> example = Example.of(filter); 
        return (List<T>)(List<?>)applicationDao.findAll(example);
    }

    @Override
    public Long add(Application application) {
        return applicationDao.save(application).getId();
    }

    @Override
    public void update(Long id, Application application) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setStatus(Long id, AppStatus status) {
        Application app = applicationDao.findById(id).get();
        app.setStatus(status);
        applicationDao.save(app);
    }

    @Override
    public List<Application> getByExperiment(Long experimentId) {
        // return applicationRepository.findByExperimentId(experimentId);
        return null;
    }

    @Override
    public void setExecutionGroup(Long id, User user) {
        Application app = applicationDao.findById(id).get();
        app.setExecutionGroup(user.getUserGroup());
        applicationDao.save(app);
    }

    @Override
    public Long addReport(long id, Report report) {
        // report.setId(id);
        report.setCreationDate(LocalDateTime.now());
        report = reportDao.save(report);
        return report.getId();
    }

}
