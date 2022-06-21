package com.mpi.alienresearch.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "Id")
public class AppEngineeringWork extends Application {

    public AppEngineeringWork() {
    }
    
}
