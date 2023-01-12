package com.mpi.alienresearch.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.SubjectDao;
import com.mpi.alienresearch.model.Subject;

@Service
public class SubjectService {
    final SubjectDao subjectRepository;
    
    public SubjectService(SubjectDao subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    
    public Subject get(Long id) {
        return subjectRepository.findById(id).get();
    }

    
    public boolean exists(Long id) {
        return subjectRepository.findById(id).isPresent();
    }

    
    public Collection<Subject> getPage(Long offset, Long limit, String[] sortvalues) {
        // TODO Auto-generated method stub
        return null;
    }

    
    public Long add(Subject subject) {
        return subjectRepository.save(subject).getId();
    }

    
    public void update(Long id, Subject subject) {
        subject.setId(id);
        subjectRepository.save(subject);
    }
}
