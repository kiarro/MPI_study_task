package com.mpi.alienresearch.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.AppType;




/**
 * Заявка
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    defaultImpl = Application.class
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = AppTechnic.class, name = "TECHNIC"),
    @JsonSubTypes.Type(value = AppAnalysis.class, name = "ANALYSIS"),
    @JsonSubTypes.Type(value = AppLanding.class, name = "LANDING")
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    private AppType type;

    private String description;

    @ManyToOne
    // @JsonIgnore
    private User creator;
    
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    // @JsonProperty("creator")
    // private Long getCreatorId(){
    //     return creator.getId();
    // }

    @ManyToOne
    // @JsonIgnore
    // @JsonIncludeProperties({"id"})
    private Experiment experiment;

    // @JsonProperty("experiment")
    // private Long getExperimentId(){
    //     return experiment.getId();
    // }

    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastStatusTransitionDate;
    
    @Enumerated(EnumType.STRING)
    private AppStatus status;

    // @ManyToOne
    private String executionGroup;
    
    // @JsonProperty("executionGroup")
    // private String getExecutionGroupId(){
    //     return executionGroup;
    // }

    public Application(Long id, AppType type, String description, User creator, Experiment experiment, LocalDateTime creationDate,
            LocalDateTime lastStatusTransitionDate, AppStatus status, String executionGroup) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.creator = creator;
        this.experiment = experiment;
        this.creationDate = creationDate;
        this.lastStatusTransitionDate = lastStatusTransitionDate;
        this.status = status;
        this.executionGroup = executionGroup;
    }

    public Application() {
    }

    public <T extends Application> T asClass() {
        return (T)this;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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

    public String getExecutionGroup() {
        return executionGroup;
    }

    public void setExecutionGroup(String executionGroup) {
        this.executionGroup = executionGroup;
    }

}
