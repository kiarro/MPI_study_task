package com.mpi.alienresearch.controllers;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpi.alienresearch.filters.ExperimentFilter;
import com.mpi.alienresearch.model.AppAnalysis;
import com.mpi.alienresearch.model.AppLanding;
import com.mpi.alienresearch.model.AppTechnic;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Experiment;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.UserGroup;
import com.mpi.alienresearch.model.enums.ExperimentStatus;
import com.mpi.alienresearch.service.ExperimentService;
import com.mpi.alienresearch.service.UserService;

import java.util.logging.Logger;


@RestController
@CrossOrigin
@RequestMapping("/experiments")
public class ExperimentController {

    private static Logger _log = Logger.getLogger(ExperimentController.class.getName());

    private final ExperimentService experimentService;
    private final UserService userService;


    public ExperimentController(ExperimentService experimentService, UserService userService) {
        this.experimentService = experimentService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addExperiment(@RequestBody Experiment experiment,
                                                    Authentication auth) {
        String username = auth.getName();
        experiment.setResearchGroup(userService.loadUserByUsername(username).getUserGroup());
        Optional<Long> id = Optional.ofNullable(experimentService.add(experiment));
        if (id.isPresent()) {
            URI uri = URI.create("/experiments/" + id.get());
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
            Experiment filter) {

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

    @PostMapping("/{id}/applications")
    public ResponseEntity<String> addApplication(@PathVariable("id") Long id, @RequestBody Application app) {
        // _log.info(String.valueOf(app instanceof AppTechnic));
        // _log.info(String.valueOf(app instanceof AppAnalysis));
        // _log.info(String.valueOf(app instanceof AppLanding));
        Optional<Long> appId = Optional.ofNullable(experimentService.addApplication(id, app));
        if (appId.isPresent()) {
            URI uri = URI.create("/applications/" + appId.get());
            // System.out.println(uri.toString());
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/reports")
    public ResponseEntity<String> addReport(@PathVariable("id") Long id, @RequestBody Report report) {
        Optional<Long> appId = Optional.ofNullable(experimentService.addReport(id, report));
        if (appId.isPresent()) {
            URI uri = URI.create("/reports/" + appId.get());
            // System.out.println(uri.toString());
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/current")
    public Collection<Experiment> getForCurrent(@RequestParam(name = "offset", defaultValue = "0") Long offset,
            @RequestParam(name = "limit", defaultValue = "10") Long limit,
            @RequestParam(name = "sort", required = false) String[] sortvalues,
            Experiment filter,
            Authentication auth) {
            
        String username = auth.getName();
        Optional<UserGroup> ug = Optional.ofNullable(userService.loadUserByUsername(username).getUserGroup());
        filter.setResearchGroup(ug.orElse(new UserGroup(null)));
        Collection<Experiment> experiments = experimentService.getPage(offset, limit, sortvalues, filter);

        return experiments;
    }

    @PostMapping("/{id}/approve")
    public void accept(@PathVariable("id") Long id) {
        experimentService.setStatus(id, ExperimentStatus.IN_PROGRESS);
    }
    @PostMapping("/{id}/decline")
    public void decline(@PathVariable("id") Long id) {
        experimentService.setStatus(id, ExperimentStatus.DECLINED);
    }
    @PostMapping("/{id}/close")
    public void close(@PathVariable("id") Long id) {
        experimentService.setStatus(id, ExperimentStatus.FINISHED);
    }
    @PostMapping("/{id}/unclose")
    public void unclose(@PathVariable("id") Long id) {
        experimentService.setStatus(id, ExperimentStatus.IN_PROGRESS);
    }
    @PostMapping("/{id}/finishing")
    public void finishing(@PathVariable("id") Long id) {
        experimentService.setStatus(id, ExperimentStatus.FINISHING);
    }
}
