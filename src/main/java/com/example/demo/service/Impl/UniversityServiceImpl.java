package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;

@Service
public class UniversityServiceImpl implements UniversityService {

    // REQUIRED FIELD NAME
    private final UniversityRepository repository;

    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public University createUniversity(University university) {
        return repository.save(university);
    }

    @Override
    public University updateUniversity(Long id, University university) {
        university.setId(id);
        return repository.save(university);
    }

    @Override
    public University getUniversityById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<University> getAllUniversities() {
        return repository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        repository.findById(id).ifPresent(u -> {
            u.setActive(false);
            repository.save(u);
        });
    }
}
