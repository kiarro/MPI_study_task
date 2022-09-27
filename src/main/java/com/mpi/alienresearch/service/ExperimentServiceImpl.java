package com.mpi.alienresearch.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.ApplicationDao;
import com.mpi.alienresearch.dao.ExperimentDao;
import com.mpi.alienresearch.dao.ReportDao;
import com.mpi.alienresearch.filters.ExperimentFilter;
import com.mpi.alienresearch.model.AppAnalysis;
import com.mpi.alienresearch.model.AppTechnic;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.UserGroup;
import com.mpi.alienresearch.model.enums.AppType;
import com.mpi.alienresearch.model.enums.ExperimentStatus;
import com.mpi.alienresearch.state.State;

@Service
public class ExperimentServiceImpl implements ExperimentService{

    final ExperimentDao experimentDao;
    
    final ReportDao reportDao;
    
    final ApplicationDao applicationDao;

    public ExperimentServiceImpl(ExperimentDao experimentDao, 
                                ReportDao reportDao,
                                ApplicationDao appTechnicDao) {
        this.experimentDao = experimentDao;
        this.reportDao = reportDao;
        this.applicationDao = appTechnicDao;
    }

    @Override
    public Experiment get(long id) {
        return experimentDao.findById(id).get();
    }

    /**
     * Just return all
     */
    @Override
    public Collection<Experiment> getPage(Long offset, Long limit, String[] sortvalues, Experiment filter) {
        Example<Experiment> example = Example.of(filter);
        return experimentDao.findAll(example);
    }

    @Override
    public Long add(Experiment experiment) {
        experiment.setCreationTime(LocalDateTime.now());
        experiment.setStatus(ExperimentStatus.CREATED);
        experiment = experimentDao.save(experiment);
        return experiment.getId();
    }

    @Override
    public void update(long id, Experiment experiment) {
        experiment.setId(id);
        experimentDao.save(experiment);
    }

    @Override
    public List<Experiment> getByGroup(UserGroup group) {
        return experimentDao.findByResearchGroup(group);
    }

    @Override
    public Long addApplication(long id, Application app) {
        // app.setId(id);
        app.setCreationDate(LocalDateTime.now());
        app.setCreator(State.getCurrentUser());
        // AppType at = app.getType();
        // switch (at) {
        //     case ANALYSIS:
        //         app = (AppAnalysis)app;
        //         break;
        //     case TECHNIC:
        //         app = (AppTechnic)app;
        //         break;
        // }
        app = applicationDao.save(app);
        return app.getId();
    }

    @Override
    public Long addReport(long id, Report report) {
        // report.setId(id);
        report.setCreationDate(LocalDateTime.now());
        report = reportDao.save(report);
        return report.getId();
    }

    @Override
    public void setStatus(long id, ExperimentStatus status) {
        Experiment experiment = experimentDao.findById(id).get();
        experiment.setStatus(status);
        experimentDao.save(experiment);
    }
    
}
