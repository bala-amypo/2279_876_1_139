package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.CourseService;

import java.util.List;
@Service

public class CourseServiceImpl implements CourseService {

    // ⚠️ Test injects these via reflection – field names MUST match
    private CourseRepository repo;
    private UniversityRepository univRepo;

    @Override
    public Course createCourse(Course course) {

        if (course == null || course.getUniversity() == null || course.getUniversity().getId() == null) {
            throw new IllegalArgumentException("University required");
        }

        if (course.getCreditHours() <= 0) {
            throw new IllegalArgumentException("Credit hours must be > 0");
        }

        Long univId = course.getUniversity().getId();
        University u = univRepo.findById(univId)
                .orElseThrow(() -> new RuntimeException("University not found"));

        if (course.getCourseCode() != null) {
            repo.findByUniversityIdAndCourseCode(univId, course.getCourseCode())
                    .ifPresent(c -> {
                        throw new IllegalArgumentException("Duplicate course code");
                    });
        }

        course.setUniversity(u);
        course.setActive(true);
        return repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existing.setCourseName(course.getCourseName());
        existing.setCourseCode(course.getCourseCode());
        existing.setCreditHours(course.getCreditHours());

        return repo.save(existing);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public void deactivateCourse(Long id) {
        Course c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        c.setActive(false);
        repo.save(c);
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return repo.findByUniversityIdAndActiveTrue(universityId);
    }
}

