// Package: com.example.transferportal.service.impl

package com.example.transferportal.service.impl;

import com.example.transferportal.entity.*;
import com.example.transferportal.entity.exception.ResourceNotFoundException;
import com.example.transferportal.repository.*;
import com.example.transferportal.service.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// =============================
// 1. UniversityServiceImpl
// =============================
@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University createUniversity(University univ) {
        return universityRepository.save(univ);
    }

    @Override
    public University updateUniversity(Long id, University univ) {
        University existing = getUniversityById(id);
        existing.setName(univ.getName());
        existing.setActive(univ.isActive());
        return universityRepository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University university = getUniversityById(id);
        university.setActive(false);
        universityRepository.save(university);
    }
}

// =============================
// 2. CourseServiceImpl
// =============================
@Service
class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = getCourseById(id);
        existing.setCourseName(course.getCourseName());
        existing.setCredits(course.getCredits());
        existing.setActive(course.isActive());
        return courseRepository.save(existing);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return courseRepository.findByUniversityIdAndActiveTrue(universityId);
    }

    @Override
    public void deactivateCourse(Long id) {
        Course course = getCourseById(id);
        course.setActive(false);
        courseRepository.save(course);
    }
}

// =============================
// 3. CourseContentTopicServiceImpl
// =============================
@Service
class CourseContentTopicServiceImpl implements CourseContentTopicService {

    private final CourseContentTopicRepository topicRepository;

    public CourseContentTopicServiceImpl(CourseContentTopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public List<CourseContentTopic> getTopicsForCourse(Long courseId) {
        return topicRepository.findByCourseId(courseId);
    }

    @Override
    public CourseContentTopic getTopicById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic topic) {
        CourseContentTopic existing = getTopicById(id);
        existing.setTopicName(topic.getTopicName());
        return topicRepository.save(existing);
    }
}

// =============================
// 4. TransferRuleServiceImpl
// =============================
@Service
class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository ruleRepository;

    public TransferRuleServiceImpl(TransferRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public TransferRule createRule(TransferRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = getRuleById(id);
        existing.setMinOverlapPercentage(rule.getMinOverlapPercentage());
        existing.setMaxCreditDifference(rule.getMaxCreditDifference());
        existing.setActive(rule.isActive());
        return ruleRepository.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer rule not found"));
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
        return ruleRepository.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceId, targetId)
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule rule = getRuleById(id);
        rule.setActive(false);
        ruleRepository.save(rule);
    }
}

// =============================
// 5. TransferEvaluationServiceImpl
// =============================
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
