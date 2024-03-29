package com.mpi.alienresearch.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.controllers.WebSocketController;
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
// import com.mpi.alienresearch.model.UserGroup;
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
    
    @Autowired
    WebSocketController webSocketController;
    
    public Experiment get(long id) {
        return experimentDao.findById(id).get();
    }

    /**
     * Just return all
     */
    
    public Collection<Experiment> getPage(Long offset, Long limit, String[] sortvalues, Experiment filter) {
        Sort s = Sort.unsorted();
        for (int i = 0; i < sortvalues.length; i++) {
            String sort_ = sortvalues[i];
            Sort.Direction direction = sort_.charAt(0)=='-'?Sort.Direction.DESC:Sort.Direction.ASC;
            s = s.and(Sort.by(direction, sort_.substring(1)));
        }
        Example<Experiment> example = Example.of(filter, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
        return experimentDao.findAll(example, s);
    }

    
    public Long add(Experiment experiment) {
        experiment.setCreationTime(LocalDateTime.now());
        experiment.setStatus(ExperimentStatus.CREATED);
        experiment = experimentDao.save(experiment);

        webSocketController.sendDirector("New experiment was created");
        return experiment.getId();
    }

    
    public void update(long id, Experiment experiment) {
        experiment.setId(id);
        experimentDao.save(experiment);
    }

    
    public List<Experiment> getByGroup(String group) {
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

        webSocketController.sendDirector("New application for experiment "+app.getExperiment().getId().toString());

        return app.getId();
    }

    
    public Long addReport(long id, Report report) {
        Experiment a = new Experiment();
        a.setId(id);
        report.setExperiment(a);
        report.setCreationDate(LocalDateTime.now());
        report = reportDao.save(report);
        return report.getId();
    }

    
    public void setStatus(long id, ExperimentStatus status) {
        Experiment experiment = experimentDao.findById(id).get();
        experiment.setStatus(status);
        experimentDao.save(experiment);
        if (status == ExperimentStatus.CREATED 
            || status == ExperimentStatus.FINISHING) {
            webSocketController.sendDirector("Experiment "+id+" now in state "+status.name());
        } else {
            webSocketController.sendGroup("Experiment "+id+" now in state "+status.name(), experiment.getResearchGroup());
        }
    }
    
}
