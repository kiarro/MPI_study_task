package com.mpi.alienresearch.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.mpi.alienresearch.dao.SubjectDao;
import com.mpi.alienresearch.model.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {
    final SubjectDao subjectRepository;
    
    public SubjectServiceImpl(SubjectDao subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject get(Long id) {
        return subjectRepository.findById(id).get();
    }

    @Override
    public boolean exists(Long id) {
        return subjectRepository.findById(id).isPresent();
    }

    @Override
    public Collection<Subject> getPage(Long offset, Long limit, String[] sortvalues) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long add(Subject report) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Long id, Subject report) {
        // TODO Auto-generated method stub
        
    }
}
