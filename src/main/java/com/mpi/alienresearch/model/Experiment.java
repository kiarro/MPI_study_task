package com.mpi.alienresearch.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;

import com.mpi.alienresearch.model.enums.ExperimentStatus;

import javax.persistence.*;

/**
 * Эксперимент
 */
@Entity
@Table(name = "experiments")
public class Experiment {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @Enumerated(EnumType.STRING)
    ExperimentStatus state;

    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExperimentStatus getState() {
        return state;
    }

    public void setState(ExperimentStatus state) {
        this.state = state;
    }

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    Long researcherGroup;

    List<Report> reports;
    List<Application> applications;

    @Column(columnDefinition = "DATE")
    LocalDateTime creationDate;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Experiment() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Report> getReports() {
        return reports;
    }
    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
    public List<Application> getApplications() {
        return applications;
    }
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
    
    public Long getResearcherGroup() {
        return researcherGroup;
    }

    public void setResearcherGroup(Long researcherGroup) {
        this.researcherGroup = researcherGroup;
    }

    public void updateWith(Experiment experiment) {
        setResearcherGroup(experiment.getResearcherGroup());
        setTitle(experiment.getTitle());
        setDescription(experiment.getDescription());
    }
}
