package com.example.demo.service;

import com.example.demo.entity.Program;

import java.util.List;

public interface ProgramService {

    Program createProgram(Program program);

    List<Program> getProgramsByUniversity(Long universityId);

    Program getProgramById(Long id);
}