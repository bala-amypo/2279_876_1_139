package com.example.demo.service;

import com.example.demo.entity.TransferEvaluation;

public interface TransferEvaluationService {
    
    String evaluateTransfer(Long studentId, Long courseId);  // must match controller call

    TransferEvaluation getEvaluationById(Long evaluationId); // must match controller call
}
