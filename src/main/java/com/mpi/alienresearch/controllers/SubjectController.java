package com.mpi.alienresearch.controllers;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpi.alienresearch.model.Subject;
import com.mpi.alienresearch.service.SubjectService;

@RestController
@CrossOrigin
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @GetMapping
    public Collection<Subject> getAll(@RequestParam(name = "offset", defaultValue = "0") Long offset,
            @RequestParam(name = "limit", defaultValue = "10") Long limit,
            @RequestParam(name = "sort", required = false) String[] sortvalues) {

        Collection<Subject> reports = subjectService.getPage(offset, limit, sortvalues);

        return reports;
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable("id") Long id) {
        return subjectService.get(id);
    }

    @GetMapping("/exists")
    public boolean getExists( @RequestParam(name = "id") Long id) {
        return subjectService.exists(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSubject(@PathVariable("id") long id, @RequestBody Subject subject) {
        subjectService.update(id, subject);

        return ResponseEntity.noContent().build();
    }

}
