package com.mpi.alienresearch.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime creationDate;

    @ManyToOne
    @JsonIncludeProperties({"id"})
    private Experiment experiment;

    @ManyToOne
    
    @JsonIncludeProperties({"id"})
    private Application application;

    public Report(Long id, String title, String content, LocalDateTime creationDate, Experiment experiment,
            Application application) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.experiment = experiment;
        this.application = application;
    }

    public Report() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

}
