package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class CompletedCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;
    private String grade;
    private Double credits;

    public CompletedCourse() {
    }

    public CompletedCourse(String courseCode, String grade, Double credits) {
        this.courseCode = courseCode;
        this.grade = grade;
        this.credits = credits;
    }

    public Long getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }
}