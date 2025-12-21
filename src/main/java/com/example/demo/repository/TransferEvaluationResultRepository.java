package com.example.demo.repository;

import com.example.demo.entity.TransferEvaluationResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferEvaluationResultRepository
        extends JpaRepository<TransferEvaluationResult, Long> {

    List<TransferEvaluationResult> findBySourceCourseIdOrTargetCourseId(
            Long sourceCourseId,
            Long targetCourseId
    );
}