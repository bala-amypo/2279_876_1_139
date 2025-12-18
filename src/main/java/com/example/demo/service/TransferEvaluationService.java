package com.example.transferportal.service;

import com.example.transferportal.entity.TransferEvaluationResult;

import java.util.List;

public interface TransferEvaluationService {

    TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId);

    TransferEvaluationResult getEvaluationById(Long id);

    List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId);
}
