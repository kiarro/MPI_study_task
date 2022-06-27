package com.mpi.alienresearch.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "credentials")
public class Credentials {

    @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id = 0l;

    private String username;
    private String password;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", updatable = false, insertable = false)
    @MapsId
    @JsonIgnore
    private User user;

    public Credentials(String username, String password, User user) {
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public Credentials() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
