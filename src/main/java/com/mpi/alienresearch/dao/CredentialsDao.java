package com.mpi.alienresearch.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpi.alienresearch.model.Credentials;

@Repository 
public interface CredentialsDao extends JpaRepository<Credentials, Long> {
    
    Optional<Credentials> findByUsername(String username);
    
}
