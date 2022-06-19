package com.mpi.alienresearch.model;

/**
 * Артефакт
*/
public class Artifact {
    Integer id;
    String name;
    String description;

    
    public Artifact() {
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
