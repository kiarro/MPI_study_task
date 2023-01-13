package com.mpi.alienresearch.controllers;

import java.net.URI;
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

import com.mpi.alienresearch.model.Artifact;
import com.mpi.alienresearch.service.ArtifactService;


@RestController
@CrossOrigin
@RequestMapping("/artifacts")
public class ArtifactController {
    private final ArtifactService artifactService;

    public ArtifactController(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    @PostMapping
    public ResponseEntity<String> addArtifact(@RequestBody Artifact Artifact) {
        Optional<Long> id = Optional.ofNullable(artifactService.add(Artifact));
        if (id.isPresent()) {
            URI uri = URI.create("/artifacts/" + id.get());
            // System.out.println(uri.toString());
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public Collection<Artifact> getAll(@RequestParam(name = "offset", defaultValue = "0") Long offset,
            @RequestParam(name = "limit", defaultValue = "10") Long limit,
            @RequestParam(name = "sort", required = false, defaultValue = "") String[] sortvalues,
            Artifact filter) {
        
        return artifactService.getPage(offset, limit, sortvalues, filter);
    }

    @GetMapping("/{id}")
    public Artifact getArtifact(@PathVariable("id") Long id) {
        return artifactService.get(id);
    }

    @GetMapping("/exists")
    public boolean getExists( @RequestParam(name = "id") Long id) {
        return artifactService.exists(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateArtifact(@PathVariable("id") long id, @RequestBody Artifact Artifact) {
        artifactService.update(id, Artifact);

        return ResponseEntity.noContent().build();
    }

}
