package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseMapping;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.CourseMappingRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseMappingService;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMappingServiceImpl implements CourseMappingService {

    private final CourseMappingRepository courseMappingRepository;
    private final CourseRepository courseRepository;

    public CourseMappingServiceImpl(CourseMappingRepository courseMappingRepository,CourseRepository courseRepository) {
        this.courseMappingRepository = courseMappingRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseMapping createMapping(CourseMapping mapping) {

        Long sourceId = mapping.getSourceCourse().getId();
        Long targetId = mapping.getTargetCourse().getId();

        Course sourceCourse = courseRepository.findById(sourceId).orElseThrow(() ->new ResourceNotFoundException("Source course not found"));

        Course targetCourse = courseRepository.findById(targetId).orElseThrow(() -> new ResourceNotFoundException("Target course not found"));

        if (courseMappingRepository.existsBySourceCourseIdAndTargetCourseId(sourceId, targetId)) {

            throw new ValidationException("Course mapping already exists");
        }

        mapping.setSourceCourse(sourceCourse);
        mapping.setTargetCourse(targetCourse);

        return courseMappingRepository.save(mapping);
    }

    @Override
    public List<CourseMapping> getMappingsBySource(Long sourceCourseId) {

        if (!courseRepository.existsById(sourceCourseId)) {
            throw new ResourceNotFoundException("Source course not found");
        }

        return courseMappingRepository.findBySourceCourseId(sourceCourseId);
    }
}
