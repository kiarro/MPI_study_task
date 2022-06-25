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
@Table(name = "applications")
public abstract class Application {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private AppType type;

    private String description;

    @ManyToOne
    private User creator;

    @ManyToOne
    private Experiment experiment;

    @Column(columnDefinition = "DATE")
    private LocalDateTime lastStatusTransitionDate;
    
    @Enumerated(EnumType.STRING)
    private AppStatus status;

    @ManyToOne
    private UserGroup executionGroup;

    public Application(Long id, AppType type, String description, User creator, Experiment experiment,
            LocalDateTime lastStatusTransitionDate, AppStatus status, UserGroup executionGroup) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.creator = creator;
        this.experiment = experiment;
        this.lastStatusTransitionDate = lastStatusTransitionDate;
        this.status = status;
        this.executionGroup = executionGroup;
    }

    public Application() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public LocalDateTime getLastStatusTransitionDate() {
        return lastStatusTransitionDate;
    }

    public void setLastStatusTransitionDate(LocalDateTime lastStatusTransitionDate) {
        this.lastStatusTransitionDate = lastStatusTransitionDate;
    }

    public AppStatus getStatus() {
        return status;
    }

    public void setStatus(AppStatus status) {
        this.status = status;
    }

    public UserGroup getExecutionGroup() {
        return executionGroup;
    }

    public void setExecutionGroup(UserGroup executionGroup) {
        this.executionGroup = executionGroup;
    }

}
