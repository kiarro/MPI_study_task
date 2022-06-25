package com.mpi.alienresearch.model;

import javax.persistence.Embedded;
import javax.persistence.ManyToOne;

public class LandingPoint {

    @Embedded
    Coordinates coordinates;

    @ManyToOne
    Artifact artifact;

    @ManyToOne
    AppLanding application;

    Integer amount;

    public LandingPoint(Coordinates coordinates, Artifact artifact, AppLanding application, Integer amount) {
        this.coordinates = coordinates;
        this.artifact = artifact;
        this.application = application;
        this.amount = amount;
    }

    public LandingPoint() {
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public AppLanding getApplication() {
        return application;
    }

    public void setApplication(AppLanding application) {
        this.application = application;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
}
