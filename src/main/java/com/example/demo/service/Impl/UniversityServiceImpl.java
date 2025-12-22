package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository repository;

    @Override
    public University createUniversity(University univ) {
        repository.findByName(univ.getName()).ifPresent(u -> {
            throw new IllegalArgumentException("University already exists");
        });
        return repository.save(univ);
    }

    @Override
    public University updateUniversity(Long id, University univ) {
        University existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));
        existing.setName(univ.getName());
        existing.setAccreditationLevel(univ.getAccreditationLevel());
        existing.setCountry(univ.getCountry());
        return repository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return repository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University univ = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));
        univ.setActive(false);
        repository.save(univ);
    }
}