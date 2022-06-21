package com.mpi.alienresearch.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.ReportRepository;
import com.mpi.alienresearch.filters.ReportFilter;
import com.mpi.alienresearch.model.Report;

@Service
public class ReportServiceImpl implements ReportService {

    final ReportRepository reportRepository;
    
    public ReportServiceImpl(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }
    
    @Override
    public Report get(Long id) {
        return reportRepository.findById(id.longValue());
    }

    /**
     * Just return all
     */
    @Override
    public Collection<Report> getPage(Long offset, Long limit, String[] sortvalues, ReportFilter filter) {
        return reportRepository.findAll();
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
    public Collection<Report> getByExperiment(Long experimentId) {
        return reportRepository.findByExperimentId(experimentId);
    }

}
