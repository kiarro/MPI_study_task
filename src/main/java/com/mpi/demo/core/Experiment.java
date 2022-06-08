package com.mpi.demo.core;

import java.util.List;

/**
 * Эксперимент
 */
public class Experiment {
    String id;

    String researcherGroup;

    List<Report> reports;
    List<Application> applications;

    public Experiment() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
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
