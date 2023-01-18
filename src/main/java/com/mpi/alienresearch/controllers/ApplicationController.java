package com.mpi.alienresearch.controllers;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mpi.alienresearch.filters.ApplicationFilter;
import com.mpi.alienresearch.model.Application;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.model.UserGroup;
import com.mpi.alienresearch.model.enums.AppStatus;
import com.mpi.alienresearch.model.enums.Decision;
import com.mpi.alienresearch.service.ApplicationService;
import com.mpi.alienresearch.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/applications")
public class ApplicationController {
    
    private final ApplicationService applicationService;
    private final UserService userService;

    public ApplicationController(ApplicationService applicationService,
                                    UserService userService) {
        this.applicationService = applicationService;
        this.userService = userService;
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

    @PostMapping("/{id}/accept")
    public void acceptApplication(@PathVariable("id") Long id, @RequestBody UserGroup ug) {
        applicationService.setStatus(id, AppStatus.ACCEPTED);
        applicationService.setExecutionGroup(id, ug);
    }

    @PostMapping("/{id}/reports")
    public ResponseEntity<String> addReport(@PathVariable("id") Long id, @RequestBody Report report) {
        Optional<Long> appId = Optional.ofNullable(applicationService.addReport(id, report));
        if (appId.isPresent()) {
            URI uri = URI.create("/reports/" + appId.get());
            // System.out.println(uri.toString());
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
