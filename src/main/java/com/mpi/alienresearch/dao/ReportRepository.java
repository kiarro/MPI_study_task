package com.mpi.alienresearch.dao;

import com.mpi.alienresearch.model.Report;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository 
public interface ReportRepository extends JpaRepository<Report, Long>  {
    Report findById(long id);

    List<Report> findByExperimentId(Long experimentId);
}
