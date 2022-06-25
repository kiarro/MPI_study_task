package com.mpi.alienresearch.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Coordinates {

    @Column(name = "coord_x")
    float x;
    @Column(name = "coord_y")
    float y;
    
    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
