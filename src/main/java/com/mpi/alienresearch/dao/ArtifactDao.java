package com.mpi.alienresearch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpi.alienresearch.model.Artifact;

@Repository
public interface ArtifactDao extends JpaRepository<Artifact, Long> {
    
}
