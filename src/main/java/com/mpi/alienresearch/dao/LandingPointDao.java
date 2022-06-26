package com.mpi.alienresearch.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpi.alienresearch.model.LandingPoint;

@Repository
public interface LandingPointDao extends JpaRepository<LandingPoint, Long> {
    
    List<LandingPoint> findByApplication_Id(Long id);

}
