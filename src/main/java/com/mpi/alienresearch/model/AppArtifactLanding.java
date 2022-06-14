package com.mpi.alienresearch.model;

import java.util.List;


public class AppArtifactLanding extends Application {

    Coordinates coordinates;
    List<ArtifactLandingPoint> landings;

    public AppArtifactLanding() {
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<ArtifactLandingPoint> getLandings() {
        return landings;
    }

    public void setLandings(List<ArtifactLandingPoint> landings) {
        this.landings = landings;
    }
    
}
