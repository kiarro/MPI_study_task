package com.mpi.alienresearch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Артефакт
*/
@Entity
@Table(name = "artifacts")
public class Artifact {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;

    private Double radiation;
    
    public Double getRadiation() {
        return radiation;
    }

    public void setRadiation(Double radiation) {
        this.radiation = radiation;
    }

    public Artifact(Long id, String name, String description, Double radiation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.radiation = radiation;
    }
    
    public Artifact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
