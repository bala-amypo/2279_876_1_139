package com.example.demo.controller;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.service.CourseContentTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class CourseContentTopicController {
    
    @Autowired
    private CourseContentTopicService topicService;

    @PostMapping
    public ResponseEntity<CourseContentTopic> createTopic(@RequestBody CourseContentTopic topic) {
        return ResponseEntity.ok(topicService.createTopic(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseContentTopic> getTopic(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseContentTopic>> getTopicsForCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(topicService.getTopicsForCourse(courseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseContentTopic> updateTopic(@PathVariable Long id, @RequestBody CourseContentTopic topic) {
        return ResponseEntity.ok(topicService.updateTopic(id, topic));
    }
}