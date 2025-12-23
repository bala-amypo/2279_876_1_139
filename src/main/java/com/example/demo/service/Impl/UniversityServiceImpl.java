package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl implements UniversityService {
    
    private UniversityRepository repository;

    @Override
    public University createUniversity(University university) {
        if (university.getName() == null || university.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name required");
        }
        if (repository.findByName(university.getName()).isPresent()) {
            throw new IllegalArgumentException("University with this name already exists");
        }
        return repository.save(university);
    }

    @Override
    public University updateUniversity(Long id, University university) {
        University existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("University not found"));
        existing.setName(university.getName());
        return repository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("University not found"));
    }

    @Override
    public void deactivateUniversity(Long id) {
        University university = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("University not found"));
        university.setActive(false);
        repository.save(university);
    }
}