package com.mpi.alienresearch.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

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

    
    public Collection<Subject> getPage(Long offset, Long limit, String[] sortvalues, Subject filter) {
        Sort s = Sort.unsorted();
        for (int i = 0; i < sortvalues.length; i++) {
            String sort_ = sortvalues[i];
            Sort.Direction direction = sort_.charAt(0)=='-'?Sort.Direction.DESC:Sort.Direction.ASC;
            s = s.and(Sort.by(direction, sort_.substring(1)));
        }
        Example<Subject> example = Example.of(filter); 
        return subjectRepository.findAll(example, s);
    }

    
    public Long add(Subject subject) {
        return subjectRepository.save(subject).getId();
    }

    
    public void update(Long id, Subject subject) {
        subject.setId(id);
        subjectRepository.save(subject);
    }
}
