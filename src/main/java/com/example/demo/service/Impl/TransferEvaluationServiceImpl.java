package com.example.demo.service.impl;

import com.example.demo.entity.TransferEvaluation;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    @Override
    public String evaluateTransfer(Long studentId, Long courseId) {
        // Dummy implementation just to pass compilation
        return "Transfer evaluation completed for student " + studentId + " and course " + courseId;
    }

    @Override
    public TransferEvaluation getEvaluationById(Long evaluationId) {
        // Dummy implementation
        TransferEvaluation evaluation = new TransferEvaluation();
        evaluation.setId(evaluationId);
        evaluation.setStatus("Pending");
        return evaluation;
    }
}
