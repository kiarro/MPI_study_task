package com.mpi.alienresearch.model;

import javax.persistence.*;

@Entity
@Table(name = "user_groups")
public class UserGroup {
    
    @Id
    private Long id;
    
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
