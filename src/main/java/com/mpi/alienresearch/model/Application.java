package com.mpi.alienresearch.model;

import java.time.LocalDateTime;

import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;

/**
 * Заявка
 */
public abstract class Application {
    Integer id;
    AppType type;

    User creator;
    
    String executionGroup;

    String description;

    LocalDateTime creationDate;
    AppStatus status;
    LocalDateTime lastStatusTransitionDate;

    Report report;

    public Application CreateApp(AppType type){
        Application a = null;
        switch (type){
            case ArtifactLanding: {
                a = new AppArtifactLanding();
            }
            case HumanAnalysis: {
                a = new AppHumanAnalysis();
            }
            case EngineeringWorks: {
                a = new AppEngineeringWork();
            }
        }
        return a;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getExecutionGroup() {
        return executionGroup;
    }

    public void setExecutionGroup(String executionGroup) {
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
