package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.entity.TransferRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TransferEvaluationResultRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    @Autowired
    private TransferEvaluationResultRepository resultRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private CourseContentTopicRepository topicRepo;

    @Autowired
    private TransferRuleRepository ruleRepo;

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {
        Course source = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        Course target = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (!source.getActive() || !target.getActive()) {
            throw new RuntimeException("Course is not active");
        }

        List<TransferRule> rules = ruleRepo.findBySourceUniversityIdAndTargetUniversityId(
                source.getUniversity().getId(),
                target.getUniversity().getId()
        );

        TransferRule activeRule = rules.stream()
                .filter(TransferRule::getActive)
                .findFirst()
                .orElse(null);

        if (activeRule == null) {
            TransferEvaluationResult result = new TransferEvaluationResult(
                    source, target, 0.0, 0, false, "No active transfer rule"
            );
            return resultRepo.save(result);
        }

        List<CourseContentTopic> sourceTopics = topicRepo.findByCourseId(sourceCourseId);
        List<CourseContentTopic> targetTopics = topicRepo.findByCourseId(targetCourseId);

        double overlap = sourceTopics.stream()
                .filter(st -> targetTopics.stream()
                        .anyMatch(tt -> tt.getTopicName().equalsIgnoreCase(st.getTopicName())))
                .mapToDouble(CourseContentTopic::getWeightPercentage)
                .sum();

        int creditDiff = Math.abs(source.getCreditHours() - target.getCreditHours());

        boolean eligible = overlap >= activeRule.getMinimumOverlapPercentage()
                && creditDiff <= activeRule.getCreditHourTolerance();

        String notes = eligible ? "Transfer eligible" : "No active rule satisfied";

        TransferEvaluationResult result = new TransferEvaluationResult(
                source, target, overlap, creditDiff, eligible, notes
        );

        return resultRepo.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepo.findBySourceCourseIdOrTargetCourseId(courseId, courseId);
    }
}