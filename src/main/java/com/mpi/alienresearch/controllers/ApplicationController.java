package com.mpi.alienresearch.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.Decision;
import com.mpi.alienresearch.service.ApplicationService;

@RestController
@CrossOrigin
@RequestMapping("/applications")
public class ApplicationController {
    
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;

    }

    @GetMapping
    public Collection<Application> getAll(@RequestParam(name = "offset", defaultValue = "0") Long offset,
            @RequestParam(name = "limit", defaultValue = "10") Long limit,
            @RequestParam(name = "sort", required = false) String[] sortvalues,
            Application filter) {

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

    @PostMapping("/{id}/set-status")
    public void decisionApplication(@PathVariable("id") Long id, @RequestParam(name = "status") AppStatus status) {
        applicationService.setStatus(id, status);
    }

}
