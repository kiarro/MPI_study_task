package com.mpi.alienresearch.controllers;

import java.util.Collection;

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

import com.mpi.alienresearch.filters.ReportFilter;
import com.mpi.alienresearch.model.Report;
import com.mpi.alienresearch.service.ReportService;

@RestController
@CrossOrigin
@RequestMapping("/reports")
public class ReportController {
    
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping
    public Collection<Report> getAll(@RequestParam(name = "offset", defaultValue = "0") Long offset,
            @RequestParam(name = "limit", defaultValue = "10") Long limit,
            @RequestParam(name = "sort", required = false) String[] sortvalues,
            ReportFilter filter) {

        Collection<Report> reports = reportService.getPage(offset, limit, sortvalues, filter);

        return reports;
    }

    @GetMapping("/{id}")
    public Report getExperiment(@PathVariable("id") Long id) {
        return reportService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putExperiment(@PathVariable("id") Long id, @RequestBody Report report) {
        Report currentReport = reportService.get(id);
        if (currentReport == null) { // experiment not found
            return ResponseEntity.notFound().build();
        }
        
        reportService.update(id, report);
        return ResponseEntity.noContent().build();
    }
}
