
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


