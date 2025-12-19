package com.example.demo.service;

import com.example.demo.entity.TransferEvaluation;

public interface TransferEvaluationService {
    String evaluateTransfer(Long studentId, Long courseId);
    TransferEvaluation getEvaluationById(Long evaluationId);
}
