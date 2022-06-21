package com.mpi.alienresearch.dao;

import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.enums.ExperimentStatus;
import com.mpi.alienresearch.service.ExperimentService;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository 
public interface ExperimentRepository extends JpaRepository<Experiment, Long>  {
    Experiment findById(long id);
    List<Experiment> findByResearcherGroup(Long researcherGroup);
}
