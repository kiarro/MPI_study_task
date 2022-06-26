package com.mpi.alienresearch.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;

@Entity
@Table(name = "apps_analysis")
public class AppAnalysis extends Application {

    @ManyToOne
    private Subject subject;

    private String description;

    public AppAnalysis(Long id, AppType type, String description, User creator, Experiment experiment,
            LocalDateTime lastStatusTransitionDate, AppStatus status, UserGroup executionGroup, Subject subject,
            String description2) {
        super(id, type, description, creator, experiment, lastStatusTransitionDate, status, executionGroup);
        this.subject = subject;
        description = description2;
    }

    public AppAnalysis() {
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
