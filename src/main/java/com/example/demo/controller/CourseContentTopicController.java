package com.example.demo.controller;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.service.CourseContentTopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "Course Content Topic")
public class CourseContentTopicController {

    private final CourseContentTopicService service;

    public CourseContentTopicController(CourseContentTopicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CourseContentTopic> create(@RequestBody CourseContentTopic topic) {
        return ResponseEntity.ok(service.create(topic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseContentTopic> update(@PathVariable Long id, @RequestBody CourseContentTopic topic) {
        return ResponseEntity.ok(service.update(id, topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseContentTopic> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getByCourse(courseId));
    }
}