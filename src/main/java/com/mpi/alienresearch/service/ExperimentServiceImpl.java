package com.mpi.alienresearch.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.ApplicationDao;
import com.mpi.alienresearch.dao.ExperimentDao;
import com.mpi.alienresearch.dao.ReportDao;
import com.mpi.alienresearch.filters.ExperimentFilter;
import com.mpi.alienresearch.model.AppTechnic;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.enums.AppType;
import com.mpi.alienresearch.model.enums.ExperimentStatus;

@Service
public class ExperimentServiceImpl implements ExperimentService{

    final ExperimentDao experimentRepository;
    
    final ReportDao reportRepository;
    
    final ApplicationDao appTechnicRepository;

    public ExperimentServiceImpl(ExperimentDao experimentRepository, ReportDao reportRepository,
            ApplicationDao appTechnicRepository) {
        this.experimentRepository = experimentRepository;
        this.reportRepository = reportRepository;
        this.appTechnicRepository = appTechnicRepository;
    }

    @Override
    public Experiment get(long id) {
        // return experimentRepository.findById(id);
        return null;
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
        // Experiment experiment1 = experimentRepository.findById(id);
        // experiment1.updateWith(experiment);
        // experimentRepository.save(experiment1);
    }

    @Override
    public Collection<Experiment> getByGroup(Long groupId) {
        // return experimentRepository.findByResearcherGroup(groupId);
        return null;
    }

    @Override
    public Long addApplication(long id, Application app) {
        app.setId(id);
        if (app.getType().equals(AppType.EngineeringWorks)) {
            app = appTechnicRepository.save((AppTechnic)app);
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
        // Experiment experiment = experimentRepository.findById(id);
        // experiment.setState(state);
        // experimentRepository.save(experiment);
    }
    
}
