package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    // REQUIRED FIELD NAMES
    private final CourseRepository repo;
    private final UniversityRepository univRepo;

    public CourseServiceImpl(CourseRepository repo, UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    @Override
    public Course createCourse(Course course) {
        return repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        course.setId(id);
        return repo.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return repo.findByUniversityIdAndActiveTrue(universityId);
    }

    @Override
    public void deactivateCourse(Long id) {
        repo.findById(id).ifPresent(c -> {
            c.setActive(false);
            repo.save(c);
        });
    }
}
