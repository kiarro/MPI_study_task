package com.mpi.alienresearch.dao;

import com.mpi.alienresearch.model.Application;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ApplicationDao extends JpaRepository<Application, Long>  {
    
}
