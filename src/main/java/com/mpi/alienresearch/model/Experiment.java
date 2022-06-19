package com.mpi.alienresearch.model;

import java.util.List;

/**
 * Эксперимент
 */
public class Experiment {
    Long id;

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String researcherGroup;

    List<Report> reports;
    List<Application> applications;

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
    
    public String getResearcherGroup() {
        return researcherGroup;
    }

    public void setResearcherGroup(String researcherGroup) {
        this.researcherGroup = researcherGroup;
    }
}
