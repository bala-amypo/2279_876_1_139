package com.example.demo.repository;

import com.example.demo.entity.TransferEvaluationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferEvaluationResultRepository
        extends JpaRepository<TransferEvaluationResult, Long> {

    List<TransferEvaluationResult> findBySourceCourseId(Long courseId);
}
