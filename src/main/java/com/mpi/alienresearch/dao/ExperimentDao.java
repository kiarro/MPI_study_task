package com.mpi.alienresearch.dao;

import com.mpi.alienresearch.model.Experiment;
// import com.mpi.alienresearch.model.UserGroup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ExperimentDao extends JpaRepository<Experiment, Long>  {
    List<Experiment> findByResearchGroup(String researchGroup);
}
