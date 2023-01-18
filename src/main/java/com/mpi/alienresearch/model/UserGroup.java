package com.mpi.alienresearch.model;

import javax.persistence.*;

@Entity
@Table(name = "user_groups")
public class UserGroup {
    
    @Id
    private Long id;

    private String description;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserGroup(Long id) {
        this.id = id;
    }

    public UserGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
