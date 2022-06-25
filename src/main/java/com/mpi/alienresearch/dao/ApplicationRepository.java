package com.mpi.alienresearch.dao;

import com.mpi.alienresearch.model.AppTechnic;
import com.mpi.alienresearch.model.Application;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository 
public interface ApplicationRepository extends JpaRepository<Application, Long>  {
    
    Application findById(long id);

    List<Application> findByExperimentId(Long experimentId);
}
