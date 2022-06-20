package com.mpi.alienresearch.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;

/**
 * Заявка
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="type")
@Table(name = "applications")
public abstract class Application {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    
    @Enumerated(EnumType.STRING)
    AppType type;

    User creator;
    
    Long executionGroup;

    String description;

    Long experimentId;

    public Long getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Long experiment_id) {
        this.experimentId = experiment_id;
    }

    @Column(columnDefinition = "DATE")
    LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    AppStatus status;
    
    @Column(columnDefinition = "DATE")
    LocalDateTime lastStatusTransitionDate;

    Report report;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppType getType() {
        return type;
    }

    public void setType(AppType type) {
        this.type = type;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Long getExecutionGroup() {
        return executionGroup;
    }

    public void setExecutionGroup(Long executionGroup) {
        this.executionGroup = executionGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public AppStatus getStatus() {
        return status;
    }

    public void setStatus(AppStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastStatusTransitionDate() {
        return lastStatusTransitionDate;
    }

    public void setLastStatusTransitionDate(LocalDateTime lastStatusTransitionDate) {
        this.lastStatusTransitionDate = lastStatusTransitionDate;
    }; 
}
