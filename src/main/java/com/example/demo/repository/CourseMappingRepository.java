package com.example.demo.repository;

import com.example.demo.entity.CourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseMappingRepository extends JpaRepository<CourseMapping, Long> {

    List<CourseMapping> findBySourceCourseId(Long sourceCourseId);

    List<CourseMapping> findByTargetCourseId(Long targetCourseId);

    Optional<CourseMapping> findBySourceCourseIdAndTargetCourseId(Long sourceCourseId, Long targetCourseId);
}
