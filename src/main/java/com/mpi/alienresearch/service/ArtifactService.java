package com.mpi.alienresearch.service;

import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.ArtifactDao;
import com.mpi.alienresearch.model.Artifact;

@Service
public class ArtifactService {
    final ArtifactDao artifactRepository;
    
    public ArtifactService(ArtifactDao artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    public Artifact get(Long id) {
        return artifactRepository.findById(id).get();
    }

    
    public boolean exists(Long id) {
        return artifactRepository.findById(id).isPresent();
    }

    
    public Long add(Artifact subject) {
        return artifactRepository.save(subject).getId();
    }

    
    public void update(Long id, Artifact subject) {
        subject.setId(id);
        artifactRepository.save(subject);
    }
}
