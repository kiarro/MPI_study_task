package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.ReportDao;
import com.mpi.alienresearch.filters.ReportFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;

@Service
public class ReportService {

    final ReportDao reportRepository;
    
    public ReportService(ReportDao reportRepository){
        this.reportRepository = reportRepository;
    }
    
    
    public Report get(Long id) {
        return reportRepository.findById(id).get();
    }

    /**
     * Just return all
     */
    
    public Collection<Report> getPage(Long offset, Long limit, String[] sortvalues, Report filter) {
        Example<Report> example = Example.of(filter, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
        return (List<Report>)reportRepository.findAll(example);
    }

    
    public Long add(Report report) {
        return reportRepository.save(report).getId();
    }

    
    public void update(Long id, Report report) {
        // TODO Auto-generated method stub

    }

    
    public List<Report> getByExperiment(Experiment experiment) {
        return reportRepository.findByExperiment(experiment);
    }

    
    public Report getByApplication(Application application) {
        return reportRepository.findByApplication(application).get();
    }

}
