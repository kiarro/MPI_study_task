package com.mpi.alienresearch.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.mpi.alienresearch.controllers.WebSocketController;
import com.mpi.alienresearch.dao.ArtifactDao;
import com.mpi.alienresearch.model.Artifact;

@Service
public class ArtifactService {
    final ArtifactDao artifactDao;
    
    public ArtifactService(ArtifactDao artifactRepository) {
        this.artifactDao = artifactRepository;
    }
    
    @Autowired
    WebSocketController webSocketController;

    public Artifact get(Long id) {
        return artifactDao.findById(id).get();
    }

    public Collection<Artifact> getPage(Long offset, Long limit, String[] sortvalues, Artifact filter){
        Sort s = Sort.unsorted();
        for (int i = 0; i < sortvalues.length; i++) {
            String sort_ = sortvalues[i];
            Sort.Direction direction = sort_.charAt(0)=='-'?Sort.Direction.DESC:Sort.Direction.ASC;
            s = s.and(Sort.by(direction, sort_.substring(1)));
        }
        Example<Artifact> example = Example.of(filter, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
        return artifactDao.findAll(example, s);
    }

    
    public boolean exists(Long id) {
        return artifactDao.findById(id).isPresent();
    }

    
    public Long add(Artifact subject) {
        return artifactDao.save(subject).getId();
    }

    
    public void update(Long id, Artifact subject) {
        subject.setId(id);
        artifactDao.save(subject);
    }
}
