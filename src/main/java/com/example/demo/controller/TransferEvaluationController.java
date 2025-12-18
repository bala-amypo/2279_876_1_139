
package com.example.transferportal.controller;

import com.example.transferportal.entity.TransferEvaluationResult;
import com.example.transferportal.service.TransferEvaluationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer-evaluations")
@Tag(name = "Transfer Evaluation")
public class TransferEvaluationController {

    private final TransferEvaluationService service;

    public TransferEvaluationController(TransferEvaluationService service) {
        this.service = service;
    }

    @PostMapping("/evaluate/{sourceCourseId}/{targetCourseId}")
    public ResponseEntity<TransferEvaluationResult> evaluate(
            @PathVariable Long sourceCourseId,
            @PathVariable Long targetCourseId) {
        return ResponseEntity.ok(service.evaluate(sourceCourseId, targetCourseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferEvaluationResult> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getByCourse(courseId));
    }
}

