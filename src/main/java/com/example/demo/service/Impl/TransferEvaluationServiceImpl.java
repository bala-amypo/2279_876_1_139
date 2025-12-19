package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;




@Service
class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final CourseRepository courseRepository;
    private final CourseContentTopicRepository topicRepository;
    private final TransferRuleRepository ruleRepository;
    private final TransferEvaluationResultRepository resultRepository;

    public TransferEvaluationServiceImpl(
            CourseRepository courseRepository,
            CourseContentTopicRepository topicRepository,
            TransferRuleRepository ruleRepository,
            TransferEvaluationResultRepository resultRepository) {
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
        this.ruleRepository = ruleRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {

        Course source = courseRepository.findById(sourceCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Source course not found"));
        Course target = courseRepository.findById(targetCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Target course not found"));

        if (!source.isActive() || !target.isActive()) {
            throw new ResourceNotFoundException("Inactive course found");
        }

        List<CourseContentTopic> sourceTopics = topicRepository.findByCourseId(sourceCourseId);
        List<CourseContentTopic> targetTopics = topicRepository.findByCourseId(targetCourseId);

        Set<String> sourceSet = new HashSet<>();
        sourceTopics.forEach(t -> sourceSet.add(t.getTopicName()));

        Set<String> targetSet = new HashSet<>();
        targetTopics.forEach(t -> targetSet.add(t.getTopicName()));

        sourceSet.retainAll(targetSet);

        double overlapPercentage = targetTopics.isEmpty()
                ? 0
                : (sourceSet.size() * 100.0) / targetTopics.size();

        TransferRule rule = ruleRepository
                .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        source.getUniversity().getId(),
                        target.getUniversity().getId()
                )
                .orElse(null);

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(source);
        result.setTargetCourse(target);

        if (rule == null) {
            result.setEligible(false);
            result.setNotes("No active transfer rule");
        } else {
            boolean eligible = overlapPercentage >= rule.getMinOverlapPercentage()
                    && Math.abs(source.getCredits() - target.getCredits()) <= rule.getMaxCreditDifference();

            result.setEligible(eligible);
            result.setNotes(eligible ? "Eligible for transfer" : "Transfer conditions not met");
        }

        return resultRepository.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepository.findBySourceCourseId(courseId);
    }
}

