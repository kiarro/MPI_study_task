package com.mpi.alienresearch.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;

@Entity
@Table(name = "apps_technic")
@PrimaryKeyJoinColumn(name = "id")
public class AppTechnic extends Application {
    
    private String content;

    public AppTechnic(Long id, String description, User creator, Experiment experiment, LocalDateTime creationDate,
            LocalDateTime lastStatusTransitionDate, AppStatus status, String executionGroup, String content) {
        super(id, AppType.TECHNIC, description, creator, experiment, creationDate, lastStatusTransitionDate, status, executionGroup);
        this.content = content;
    }

    public AppTechnic() {
        setType(AppType.TECHNIC);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
