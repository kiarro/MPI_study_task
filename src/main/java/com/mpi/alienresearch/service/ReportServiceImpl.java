package com.mpi.alienresearch.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.ReportDao;
import com.mpi.alienresearch.filters.ReportFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;

@Service
public class ReportServiceImpl implements ReportService {

    final ReportDao reportRepository;
    
    public ReportServiceImpl(ReportDao reportRepository){
        this.reportRepository = reportRepository;
    }
    
    @Override
    public Report get(Long id) {
        return reportRepository.findById(id).get();
    }

    /**
     * Just return all
     */
    @Override
    public Collection<Report> getPage(Long offset, Long limit, String[] sortvalues, Report filter) {
        Example<Report> example = Example.of(filter);
        return (List<Report>)reportRepository.findAll(example);
    }

    @Override
    public Long add(Report report) {
        return reportRepository.save(report).getId();
    }

    @Override
    public void update(Long id, Report report) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Report> getByExperiment(Experiment experiment) {
        return reportRepository.findByExperiment(experiment);
    }

    @Override
    public Report getByApplication(Application application) {
        return reportRepository.findByApplication(application).get();
    }

}
