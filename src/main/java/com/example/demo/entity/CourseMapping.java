package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_mappings")
public class CourseMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_course_id", nullable = false)
    private Course sourceCourse;

    @ManyToOne
    @JoinColumn(name = "target_course_id", nullable = false)
    private Course targetCourse;

    private String equivalencyType;
    private String minGradeRequired;

    public CourseMapping() {
    }

    public CourseMapping(
            Course sourceCourse,
            Course targetCourse,
            String equivalencyType,
            String minGradeRequired) {

        this.sourceCourse = sourceCourse;
        this.targetCourse = targetCourse;
        this.equivalencyType = equivalencyType;
        this.minGradeRequired = minGradeRequired;
    }

    public Long getId() {
        return id;
    }

    public Course getSourceCourse() {
        return sourceCourse;
    }

    public void setSourceCourse(Course sourceCourse) {
        this.sourceCourse = sourceCourse;
    }

    public Course getTargetCourse() {
        return targetCourse;
    }

    public void setTargetCourse(Course targetCourse) {
        this.targetCourse = targetCourse;
    }

    public String getEquivalencyType() {
        return equivalencyType;
    }

    public void setEquivalencyType(String equivalencyType) {
        this.equivalencyType = equivalencyType;
    }

    public String getMinGradeRequired() {
        return minGradeRequired;
    }

    public void setMinGradeRequired(String minGradeRequired) {
        this.minGradeRequired = minGradeRequired;
    }
}