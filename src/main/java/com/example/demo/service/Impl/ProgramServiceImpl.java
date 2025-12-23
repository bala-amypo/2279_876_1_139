package com.example.demo.service.impl;

import com.example.demo.entity.Program;
import com.example.demo.entity.University;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ProgramRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.ProgramService;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final UniversityRepository universityRepository;

    public ProgramServiceImpl(ProgramRepository programRepository,UniversityRepository universityRepository) {
        this.programRepository = programRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Program createProgram(Program program) {

        Long universityId = program.getUniversity().getId();

        University university = universityRepository.findById(universityId).orElseThrow(()->new ResourceNotFoundException("University not found"));

        if (programRepository.existsByUniversityIdAndNameIgnoreCase(
                universityId, program.getName())) {

            throw new ValidationException("Program already exists for this university");
        }

        program.setUniversity(university);
        return programRepository.save(program);
    }

    @Override
    public List<Program> getProgramsByUniversity(Long universityId) {

        if (!universityRepository.existsById(universityId)) {
            throw new ResourceNotFoundException("University not found");
        }

        return programRepository.findByUniversityId(universityId);
    }

    @Override
    public Program getProgramById(Long id) {

        return programRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Program not found"));
    }
}