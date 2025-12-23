package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    CourseRepository courseRepo;
    CourseContentTopicRepository topicRepo;
    TransferRuleRepository ruleRepo;
    TransferEvaluationResultRepository resultRepo;

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {

        Course src = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new RuntimeException("Source course not found"));
        Course tgt = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new RuntimeException("Target course not found"));

        if (!Boolean.TRUE.equals(src.getActive()) || !Boolean.TRUE.equals(tgt.getActive())) {
            throw new IllegalArgumentException("Course must be active");
        }

        List<CourseContentTopic> srcTopics = topicRepo.findByCourseId(sourceCourseId);
        List<CourseContentTopic> tgtTopics = topicRepo.findByCourseId(targetCourseId);

        double matched = 0;
        double total = srcTopics.stream()
                .mapToDouble(t -> t.getWeightPercentage() == null ? 0 : t.getWeightPercentage())
                .sum();

        if (total == 0) total = 100;

        for (CourseContentTopic s : srcTopics) {
            for (CourseContentTopic t : tgtTopics) {
                if (s.getTopicName().equalsIgnoreCase(t.getTopicName())) {
                    matched += Math.min(
                            s.getWeightPercentage() == null ? 0 : s.getWeightPercentage(),
                            t.getWeightPercentage() == null ? 0 : t.getWeightPercentage());
                }
            }
        }

        double overlap = (matched / total) * 100;
        int creditDiff = Math.abs(src.getCreditHours() - tgt.getCreditHours());

        List<TransferRule> rules = ruleRepo
                .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        src.getUniversity().getId(),
                        tgt.getUniversity().getId());

        boolean eligible = false;
        for (TransferRule r : rules) {
            if (overlap >= r.getMinimumOverlapPercentage()
                    && creditDiff <= (r.getCreditHourTolerance() == null ? 0 : r.getCreditHourTolerance())) {
                eligible = true;
                break;
            }
        }

        TransferEvaluationResult res = new TransferEvaluationResult();
        res.setOverlapPercentage(overlap);
        res.setCreditHourDifference(creditDiff);
        res.setIsEligibleForTransfer(eligible);
        res.setNotes(eligible ? "Eligible" :
                rules.isEmpty() ? "No active transfer rule" : "No active rule satisfied");

        return resultRepo.save(res);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long sourceCourseId) {
        return resultRepo.findBySourceCourseId(sourceCourseId);
    }
}