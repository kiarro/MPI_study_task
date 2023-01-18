package com.mpi.alienresearch.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "landing_points")
public class LandingPoint {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Coordinates coordinates;

    @ManyToOne
    private Artifact artifact;

    @ManyToOne
    @JoinColumn(name="application")
    @JsonBackReference
    private AppLanding application;

    private Integer amount;

    public LandingPoint(Long id, Coordinates coordinates, Artifact artifact, AppLanding application, Integer amount) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
