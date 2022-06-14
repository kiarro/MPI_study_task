package com.mpi.alienresearch.model;

public class ArtifactLandingPoint {
    Coordinates coordinates;
    Artifact artifact;
    Integer amount;
    
    public ArtifactLandingPoint() {
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
}
