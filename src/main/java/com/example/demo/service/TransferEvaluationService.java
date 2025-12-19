package com.example.demo.service;

import com.example.demo.entity.TransferEvaluation; // Make sure this exists

public interface TransferEvaluationService {

    // Method to evaluate a transfer
    String evaluateTransfer(Long studentId, Long courseId);

    // Method to get a TransferEvaluation by its ID
    TransferEvaluation getEvaluationById(Long evaluationId);
}
