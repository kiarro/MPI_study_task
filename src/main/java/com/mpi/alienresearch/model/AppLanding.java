package com.mpi.alienresearch.model;

import java.time.LocalDateTime;
import java.util.List;

import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;


public class AppLanding extends Application {

    public AppLanding(Long id, AppType type, String description, User creator, Experiment experiment,
            LocalDateTime lastStatusTransitionDate, AppStatus status, UserGroup executionGroup) {
        super(id, type, description, creator, experiment, lastStatusTransitionDate, status, executionGroup);
    }

    public AppLanding() {
    } 
    
}