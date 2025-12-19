package com.example.demo.controller;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferEvaluationController {

    @Autowired
    private TransferEvaluationService service;

    @GetMapping("/evaluate/{studentId}/{courseId}")
    public String evaluateTransfer(@PathVariable Long studentId, @PathVariable Long courseId) {
        return service.evaluateTransfer(studentId, courseId);
    }

    @GetMapping("/evaluation/{id}")
    public TransferEvaluation getEvaluation(@PathVariable Long id) {
        return service.getEvaluationById(id);
    }
}
