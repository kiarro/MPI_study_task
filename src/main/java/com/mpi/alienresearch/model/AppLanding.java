package com.mpi.alienresearch.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;


@Entity
@Table(name = "apps_landing")
public class AppLanding extends Application {

    // @Transient
    @OneToMany(
                mappedBy = "application")
    @JsonManagedReference
    private List<LandingPoint> landingPoints;

    public List<LandingPoint> getLandingPoints() {
        return landingPoints;
    }

    public void setLandingPoints(List<LandingPoint> landingPoints) {
        this.landingPoints = landingPoints;
    }

    public AppLanding(Long id, String description, User creator, Experiment experiment, LocalDateTime creationDate,
            LocalDateTime lastStatusTransitionDate, AppStatus status, String executionGroup, List<LandingPoint> landingPoints) {
        super(id, AppType.LANDING, description, creator, experiment, creationDate, lastStatusTransitionDate, status, executionGroup);
        this.landingPoints = landingPoints;
    }

    public AppLanding() {
        setType(AppType.LANDING);
    } 
    
}
