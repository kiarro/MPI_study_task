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
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.User;
// import com.mpi.alienresearch.model.UserGroup;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.Decision;

@Service
public class ApplicationService {

    final ApplicationDao applicationDao;
    final ReportDao reportDao;

    public ApplicationService(ApplicationDao applicationRepository, 
                                ReportDao reportRepository) {
        this.applicationDao = applicationRepository;
        this.reportDao = reportRepository;
    }

    
    public Application get(Long id) {
        return applicationDao.findById(id).get();
    }

    /**
     * Just returns all
     */
    
    public <T extends Application> List<T> getPage(Long offset, Long limit, String[] sortvalues, Application filter) {
        Example<Application> example = Example.of(filter); 
        return (List<T>)(List<?>)applicationDao.findAll(example);
    }

    
    public Long add(Application application) {
        return applicationDao.save(application).getId();
    }

    
    public void update(Long id, Application application) {
        application.setId(id);
        applicationDao.save(application);
    }

    
    public void setStatus(Long id, AppStatus status) {
        Application app = applicationDao.findById(id).get();
        app.setStatus(status);
        applicationDao.save(app);
    }

    
    public List<Application> getByExperiment(Long experimentId) {
        Experiment e = new Experiment(); e.setId(experimentId);
        return applicationDao.findByExperiment(e);
        // return null;
    }

    
    public void setExecutionGroup(Long id, String group) {
        Application app = applicationDao.findById(id).get();
        app.setExecutionGroup(group);
        applicationDao.save(app);
    }

    
    public Long addReport(long id, Report report) {
        Application a = new Application();
        a.setId(id);
        report.setApplication(a);
        report.setCreationDate(LocalDateTime.now());
        report = reportDao.save(report);
        return report.getId();
    }

}
