package com.mpi.alienresearch.controllers;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpi.alienresearch.filters.ExperimentFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.service.ExperimentService;

@RestController
@RequestMapping("/experiments")
public class ExperimentController {

    private ExperimentService experimentService;

    public ExperimentController() {
        super();

    }

    @PostMapping
    public ResponseEntity<String> addExperiment(@RequestBody Experiment experiment) {
        Optional<Long> id = Optional.ofNullable(experimentService.add(experiment));
        if (id.isPresent()) {
            URI uri = URI.create("/experiments/" + id);
            // System.out.println(uri.toString());
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public Collection<Experiment> getAll(@RequestParam(name = "offset", defaultValue = "0") Long offset,
            @RequestParam(name = "limit", defaultValue = "10") Long limit,
            @RequestParam(name = "sort", required = false) String[] sortvalues,
            ExperimentFilter filter) {

        Collection<Experiment> experiments = experimentService.getPage(offset, limit, sortvalues, filter);

        return experiments;
    }

    @GetMapping("/{id}")
    public Experiment getExperiment(@PathVariable("id") Long id) {
        return experimentService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putExperiment(@PathVariable("id") Long id, @RequestBody Experiment experiment) {
        Experiment currentExperiment = experimentService.get(id);
        if (currentExperiment == null) { // experiment not found
            return ResponseEntity.notFound().build();
        }
        // experiment exists -> update it
        experimentService.update(id, experiment);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/archive")
    public void postArchive(@PathVariable("id") String id) {
        experimentService.madeArchive(id);
    }

    @PostMapping("/{id}/applications")
    public ResponseEntity<String> addApplication(@PathVariable("id") Long id, @RequestBody Application app) {
        Optional<Long> appId = Optional.ofNullable(experimentService.addApplication(app));
        if (appId.isPresent()) {
            URI uri = URI.create("/applications/" + appId);
            // System.out.println(uri.toString());
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/reports")
    public ResponseEntity<String> addReport(@PathVariable("id") Long id, @RequestBody Report report) {
        Optional<Long> appId = Optional.ofNullable(experimentService.addReport(report));
        if (appId.isPresent()) {
            URI uri = URI.create("/reports/" + appId);
            // System.out.println(uri.toString());
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
