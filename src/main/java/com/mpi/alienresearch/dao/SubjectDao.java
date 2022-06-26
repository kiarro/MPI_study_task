package com.mpi.alienresearch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpi.alienresearch.model.Subject;

@Repository 
public interface SubjectDao extends JpaRepository<Subject, Long> {
    
}
