
package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseContentTopicService;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopicService {

    // REQUIRED FIELD NAMES (DO NOT CHANGE)
    private final CourseContentTopicRepository repo;
    private final CourseRepository courseRepo;

    public CourseContentTopicServiceImpl(
            CourseContentTopicRepository repo,
            CourseRepository courseRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {
        return repo.save(topic);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic topic) {
        topic.setId(id);
        return repo.save(topic);
    }

    @Override
    public CourseContentTopic getTopicById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<CourseContentTopic> getTopicsForCourse(Long courseId) {
        return repo.findByCourseId(courseId);
    }
}
