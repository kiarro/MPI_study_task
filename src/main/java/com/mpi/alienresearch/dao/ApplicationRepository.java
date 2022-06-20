package com.mpi.alienresearch.dao;

import com.mpi.alienresearch.model.AppEngineeringWork;
import com.mpi.alienresearch.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<Application, Long>  {
    
    AppEngineeringWork findById(long id);
}
