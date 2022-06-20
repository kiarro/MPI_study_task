package com.mpi.alienresearch.controllers;

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

import com.mpi.alienresearch.filters.ApplicationFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.enums.Decision;
import com.mpi.alienresearch.service.ApplicationService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    
    private ApplicationService applicationService;

    public ApplicationController() {
        super();

    }

    @GetMapping
    public Collection<Application> getAll(@RequestParam(name = "offset", defaultValue = "0") Long offset,
            @RequestParam(name = "limit", defaultValue = "10") Long limit,
            @RequestParam(name = "sort", required = false) String[] sortvalues,
            ApplicationFilter filter) {

        Collection<Application> applications = applicationService.getPage(offset, limit, sortvalues, filter);

        return applications;
    }

    @GetMapping("/{id}")
    public Application getExperiment(@PathVariable("id") Long id) {
        return applicationService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putExperiment(@PathVariable("id") Long id, @RequestBody Application experiment) {
        Application currentApplication = applicationService.get(id);
        if (currentApplication == null) { // experiment not found
            return ResponseEntity.notFound().build();
        }
        // experiment exists -> update it
        applicationService.update(id, experiment);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/set-decision")
    public void decisionApplication(@PathVariable("id") Long id, @RequestParam(name = "decision") Decision decision) {
        decision = Optional.ofNullable(decision).orElse(Decision.UNDECIDED);

        applicationService.setDecision(id, decision);
    }

    @PostMapping("/{id}/set-accepted")
    public void acceptedApplication(@PathVariable("id") Long id, @RequestParam(name = "accepted", required = true) Boolean accepted) {
        applicationService.setAccepted(id, accepted);
    }

    @PostMapping("/{id}/set-done")
    public void doneApplication(@PathVariable("id") Long id, @RequestBody Report report) {
        applicationService.setDone(id, report);
    }
}
