package com.mpi.alienresearch.dao;

import com.mpi.alienresearch.model.Report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ReportDao extends JpaRepository<Report, Long>  {
    
}
