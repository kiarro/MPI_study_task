package com.mpi.alienresearch.dao;

import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ReportDao extends JpaRepository<Report, Long>  {
    
    Optional<Report> findByApplication(Application application);

    List<Report> findByExperiment(Experiment experiment);
}
