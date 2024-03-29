package com.mpi.alienresearch.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;

@Entity
@Table(name = "apps_analysis")
public class AppAnalysis extends Application {

    @ManyToOne
    // @JsonIncludeProperties({"id"})
    private Subject subject;

    private String analysisDescription;

    public AppAnalysis(Long id, String description, User creator, Experiment experiment, LocalDateTime creationDate,
            LocalDateTime lastStatusTransitionDate, AppStatus status, String executionGroup, Subject subject,
            String analysisDescription) {
        super(id, AppType.ANALYSIS, description, creator, experiment, creationDate, lastStatusTransitionDate, status, executionGroup);
        this.subject = subject;
        this.analysisDescription = analysisDescription;
    }

    public AppAnalysis() {
        setType(AppType.ANALYSIS);
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
