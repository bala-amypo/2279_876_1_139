package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repo;

    @Autowired
    private UniversityRepository univRepo;

    @Override
    public Course createCourse(Course course) {
        if (course.getCreditHours() == null || course.getCreditHours() <= 0) {
            throw new IllegalArgumentException("Credit hours must be > 0");
        }
        Long univId = course.getUniversity().getId();
        University univ = univRepo.findById(univId)
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));
        course.setUniversity(univ);
        return repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        existing.setCourseName(course.getCourseName());
        existing.setCourseCode(course.getCourseCode());
        existing.setCreditHours(course.getCreditHours());
        existing.setDepartment(course.getDepartment());
        existing.setDescription(course.getDescription());
        return repo.save(existing);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return repo.findByUniversityId(universityId);
    }

    @Override
    public void deactivateCourse(Long id) {
        Course course = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        course.setActive(false);
        repo.save(course);
    }
}