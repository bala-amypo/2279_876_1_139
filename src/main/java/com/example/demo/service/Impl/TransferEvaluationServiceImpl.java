package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {
    
    private CourseRepository courseRepo;
    private CourseContentTopicRepository topicRepo;
    private TransferRuleRepository ruleRepo;
    private TransferEvaluationResultRepository resultRepo;

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {
        Course sourceCourse = courseRepo.findById(sourceCourseId)
            .orElseThrow(() -> new RuntimeException("Source course not found"));
        Course targetCourse = courseRepo.findById(targetCourseId)
            .orElseThrow(() -> new RuntimeException("Target course not found"));
            
        if (!sourceCourse.isActive() || !targetCourse.isActive()) {
            throw new IllegalArgumentException("Both courses must be active");
        }

        List<CourseContentTopic> sourceTopics = topicRepo.findByCourseId(sourceCourseId);
        List<CourseContentTopic> targetTopics = topicRepo.findByCourseId(targetCourseId);
        
        double overlapPercentage = calculateOverlap(sourceTopics, targetTopics);
        
        List<TransferRule> rules = ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
            sourceCourse.getUniversity().getId(), targetCourse.getUniversity().getId());
            
        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(sourceCourse);
        result.setTargetCourse(targetCourse);
        result.setOverlapPercentage(overlapPercentage);
        
        if (rules.isEmpty()) {
            result.setIsEligibleForTransfer(false);
            result.setNotes("No active transfer rule found between universities");
        } else {
            boolean eligible = false;
            for (TransferRule rule : rules) {
                if (overlapPercentage >= rule.getMinimumOverlapPercentage()) {
                    int creditDiff = Math.abs(sourceCourse.getCreditHours() - targetCourse.getCreditHours());
                    if (creditDiff <= rule.getCreditHourTolerance()) {
                        eligible = true;
                        break;
                    }
                }
            }
            result.setIsEligibleForTransfer(eligible);
            result.setNotes(eligible ? "Transfer approved" : "No active rule satisfied all criteria");
        }
        
        return resultRepo.save(result);
    }

    private double calculateOverlap(List<CourseContentTopic> sourceTopics, List<CourseContentTopic> targetTopics) {
        if (sourceTopics.isEmpty() && targetTopics.isEmpty()) return 100.0;
        if (sourceTopics.isEmpty() || targetTopics.isEmpty()) return 0.0;
        
        double totalSourceWeight = sourceTopics.stream().mapToDouble(CourseContentTopic::getWeightPercentage).sum();
        if (totalSourceWeight == 0) totalSourceWeight = 100.0;
        
        double matchedWeight = 0.0;
        for (CourseContentTopic sourceTopic : sourceTopics) {
            for (CourseContentTopic targetTopic : targetTopics) {
                if (sourceTopic.getTopicName().equalsIgnoreCase(targetTopic.getTopicName())) {
                    matchedWeight += Math.min(sourceTopic.getWeightPercentage(), targetTopic.getWeightPercentage());
                    break;
                }
            }
        }
        
        return (matchedWeight / totalSourceWeight) * 100.0;
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Evaluation result not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepo.findBySourceCourseId(courseId);
    }
}