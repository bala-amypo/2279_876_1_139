package com.example.demo.repository;

import com.example.demo.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    List<Program> findByUniversityId(Long universityId);

    boolean existsByUniversityIdAndNameIgnoreCase(Long universityId, String name);
}
