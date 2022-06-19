package com.mpi.alienresearch.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.mpi.alienresearch.dao.ExperimentRepository;
import com.mpi.alienresearch.filters.ExperimentFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;

public class ExperimentServiceImpl implements ExperimentService{

    @Autowired
    ExperimentRepository experimentRepository;

    @Override
    public Experiment get(long id) {
        return experimentRepository.findById(id);
    }

    @Override
    public Collection<Experiment> getPage(Long offset, Long limit, String[] sortvalues, ExperimentFilter filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long add(Experiment experiment) {
        experiment = experimentRepository.save(experiment);
        return experiment.getId();
    }

    @Override
    public void update(long id, Experiment experiment) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void madeArchive(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Long addApplication(Application app) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long addReport(Report report) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Experiment> getPage() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
