
CourseController


package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return service.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course course) {
        return service.updateCourse(id, course);
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    @GetMapping("/university/{universityId}")
    public List<Course> getByUniversity(@PathVariable Long universityId) {
        return service.getCoursesByUniversity(universityId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateCourse(id);
    }
}

