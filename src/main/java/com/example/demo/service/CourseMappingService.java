package com.example.demo.service;

import com.example.demo.entity.CourseMapping;

import java.util.List;

public interface CourseMappingService {

    CourseMapping createMapping(CourseMapping mapping);

    List<CourseMapping> getMappingsBySource(Long sourceCourseId);
}