package com.mpi.alienresearch.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.ApplicationDao;
import com.mpi.alienresearch.dao.ExperimentDao;
import com.mpi.alienresearch.dao.LandingPointDao;
import com.mpi.alienresearch.dao.ReportDao;
import com.mpi.alienresearch.filters.ExperimentFilter;
import com.mpi.alienresearch.model.AppAnalysis;
import com.mpi.alienresearch.model.AppLanding;
import com.mpi.alienresearch.model.AppTechnic;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.LandingPoint;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.UserGroup;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;
import com.mpi.alienresearch.model.enums.ExperimentStatus;

@Service
public class ExperimentService {

    final ExperimentDao experimentDao;
    
    final ReportDao reportDao;
    
    final ApplicationDao applicationDao;
    
    final LandingPointDao landingPointDao;

    public ExperimentService(ExperimentDao experimentDao, 
                                ReportDao reportDao,
                                ApplicationDao appTechnicDao,
                                LandingPointDao landingPointDao) {
        this.experimentDao = experimentDao;
        this.reportDao = reportDao;
        this.applicationDao = appTechnicDao;
        this.landingPointDao = landingPointDao;
    }

    
    public Experiment get(long id) {
        return experimentDao.findById(id).get();
    }

    /**
     * Just return all
     */
    
    public Collection<Experiment> getPage(Long offset, Long limit, String[] sortvalues, Experiment filter) {
        Example<Experiment> example = Example.of(filter);
        return experimentDao.findAll(example);
    }

    
    public Long add(Experiment experiment) {
        experiment.setCreationTime(LocalDateTime.now());
        experiment.setStatus(ExperimentStatus.CREATED);
        experiment = experimentDao.save(experiment);
        return experiment.getId();
    }

    
    public void update(long id, Experiment experiment) {
        experiment.setId(id);
        experimentDao.save(experiment);
    }

    
    public List<Experiment> getByGroup(UserGroup group) {
        return experimentDao.findByResearchGroup(group);
    }

    
    public Long addApplication(long id, Application app) {
        app.setCreationDate(java.time.LocalDateTime.now());
        app.setLastStatusTransitionDate(java.time.LocalDateTime.now());
        app.setStatus(AppStatus.CREATED);
        app.setExperiment(experimentDao.getOne(id));
        
        app = applicationDao.save(app);

        if (app.getType() == AppType.LANDING){
            for (LandingPoint lp : ((AppLanding)app).getLandingPoints()) {
                landingPointDao.save(lp);
            }
        }

        return app.getId();
    }

    
    public Long addReport(long id, Report report) {
        // report.setId(id);
        report.setCreationDate(LocalDateTime.now());
        report = reportDao.save(report);
        return report.getId();
    }

    
    public void setStatus(long id, ExperimentStatus status) {
        Experiment experiment = experimentDao.findById(id).get();
        experiment.setStatus(status);
        experimentDao.save(experiment);
    }
    
}
