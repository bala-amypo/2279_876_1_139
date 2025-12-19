package com.example.demo.service;

import com.example.demo.entity.TransferEvaluationResult;
import java.util.List;

public interface TransferEvaluationService {

    TransferEvaluationResult saveEvaluation(TransferEvaluationResult result);

    List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId);
}
