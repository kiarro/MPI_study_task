package com.mpi.alienresearch.dao;

import com.mpi.alienresearch.model.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperimentRepository extends JpaRepository<Experiment, Long>  {
    Experiment findById(long id);
}
