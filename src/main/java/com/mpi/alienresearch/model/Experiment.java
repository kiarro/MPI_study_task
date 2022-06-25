package com.mpi.alienresearch.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;

import com.mpi.alienresearch.model.enums.ExperimentStatus;

import javax.persistence.*;

/**
 * Эксперимент
 */
@Entity
@Table(name = "experiments")
public class Experiment {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(columnDefinition = "DATE")
    private LocalDateTime creationTime;

    @ManyToOne
    private UserGroup researchGroup;

    @Enumerated(EnumType.STRING)
    private ExperimentStatus state;

    public Experiment(Long id, String title, String description, LocalDateTime creationTime, UserGroup researchGroup,
            ExperimentStatus state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationTime = creationTime;
        this.researchGroup = researchGroup;
        this.state = state;
    }

    public Experiment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public UserGroup getResearchGroup() {
        return researchGroup;
    }

    public void setResearchGroup(UserGroup researchGroup) {
        this.researchGroup = researchGroup;
    }

    public ExperimentStatus getState() {
        return state;
    }

    public void setState(ExperimentStatus state) {
        this.state = state;
    }
    
}
