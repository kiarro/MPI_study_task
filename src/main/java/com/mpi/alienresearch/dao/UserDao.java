package com.mpi.alienresearch.dao;

import org.springframework.stereotype.Repository;

import com.mpi.alienresearch.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository 
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findById(long id);
    Optional<User> findByUsername(String username);
    
}
