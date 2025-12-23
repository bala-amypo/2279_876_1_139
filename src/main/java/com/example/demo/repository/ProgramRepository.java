package com.example.demo.repository;

import com.example.demo.entity.Program;
import com.example.demo.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    Optional<Program> findByUniversityAndNameIgnoreCase(University university, String name);

    List<Program> findByUniversity(University university);
}
