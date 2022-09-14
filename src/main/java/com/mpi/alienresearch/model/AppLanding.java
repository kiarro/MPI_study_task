package com.mpi.alienresearch.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;


@Entity
@Table(name = "apps_landing")
public class AppLanding extends Application {

    public AppLanding(Long id, String description, User creator, Experiment experiment, LocalDateTime creationDate,
            LocalDateTime lastStatusTransitionDate, AppStatus status, UserGroup executionGroup) {
        super(id, AppType.LANDING, description, creator, experiment, creationDate, lastStatusTransitionDate, status, executionGroup);
    }

    public AppLanding() {
    } 
    
}
