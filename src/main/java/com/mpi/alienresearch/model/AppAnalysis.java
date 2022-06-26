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

    private String analysisDescription;

    public AppAnalysis(Long id, String description, User creator, Experiment experiment,
            LocalDateTime lastStatusTransitionDate, AppStatus status, UserGroup executionGroup, Subject subject,
            String analysisDescription) {
        super(id, AppType.Analysis, description, creator, experiment, lastStatusTransitionDate, status, executionGroup);
        this.subject = subject;
        this.analysisDescription = analysisDescription;
    }

    public AppAnalysis() {
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getAnalysisDescription() {
        return analysisDescription;
    }

    public void setAnalysisDescription(String description) {
        this.analysisDescription = description;
    }
    
}
