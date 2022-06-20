package com.mpi.alienresearch.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.mpi.alienresearch.dao.ApplicationRepository;
import com.mpi.alienresearch.dao.ExperimentRepository;
import com.mpi.alienresearch.dao.ReportRepository;
import com.mpi.alienresearch.filters.ExperimentFilter;
import com.mpi.alienresearch.model.AppEngineeringWork;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.enums.AppType;
import com.mpi.alienresearch.model.enums.ExperimentStatus;

public class ExperimentServiceImpl implements ExperimentService{

    @Autowired
    ExperimentRepository experimentRepository;
    
    @Autowired
    ReportRepository reportRepository;
    
    @Autowired
    ApplicationRepository appTechnicRepository;

    @Override
    public Experiment get(long id) {
        return experimentRepository.findById(id);
    }

    /**
     * Just return all
     */
    @Override
    public Collection<Experiment> getPage(Long offset, Long limit, String[] sortvalues, ExperimentFilter filter) {
        return experimentRepository.findAll();
    }

    @Override
    public Long add(Experiment experiment) {
        experiment = experimentRepository.save(experiment);
        return experiment.getId();
    }

    @Override
    public void update(long id, Experiment experiment) {
        Experiment experiment1 = experimentRepository.findById(id);
        experiment1.updateWith(experiment);
        experimentRepository.save(experiment1);
    }

    @Override
    public Collection<Experiment> getByGroup(Long groupId) {
        return experimentRepository.findByResearcherGroup(groupId);
    }

    @Override
    public Long addApplication(long id, Application app) {
        app.setId(id);
        if (app.getType().equals(AppType.EngineeringWorks)) {
            app = appTechnicRepository.save((AppEngineeringWork)app);
        }
        return app.getId();
    }

    @Override
    public Long addReport(long id, Report report) {
        report.setId(id);
        report = reportRepository.save(report);
        return report.getId();
    }

    @Override
    public void setState(long id, ExperimentStatus state) {
        Experiment experiment = experimentRepository.findById(id);
        experiment.setState(state);
        experimentRepository.save(experiment);
    }
    
}
