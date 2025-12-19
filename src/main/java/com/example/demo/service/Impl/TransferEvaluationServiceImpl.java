package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.TransferEvaluationResultRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferEvaluationService;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    // REQUIRED FIELD NAMES
    private final TransferEvaluationResultRepository resultRepo;
    private final CourseRepository courseRepo;
    private final CourseContentTopicRepository topicRepo;
    private final TransferRuleRepository ruleRepo;

    public TransferEvaluationServiceImpl(
            TransferEvaluationResultRepository resultRepo,
            CourseRepository courseRepo,
            CourseContentTopicRepository topicRepo,
            TransferRuleRepository ruleRepo) {
        this.resultRepo = resultRepo;
        this.courseRepo = courseRepo;
        this.topicRepo = topicRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {
        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setEvaluatedAt(new Timestamp(System.currentTimeMillis()));
        result.setEligibleForTransfer(false);
        result.setNotes("No active transfer rule");
        return resultRepo.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id).orElse(null);
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepo.findBySourceCourseId(courseId);
    }
}
