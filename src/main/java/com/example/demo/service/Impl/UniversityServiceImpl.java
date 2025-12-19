package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University createUniversity(University univ) {
        return universityRepository.save(univ);
    }

    @Override
    public University updateUniversity(Long id, University univ) {
        University existing = getUniversityById(id);
        existing.setName(univ.getName());
        existing.setActive(univ.isActive());
        return universityRepository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University university = getUniversityById(id);
        university.setActive(false);
        universityRepository.save(university);
    }
}
