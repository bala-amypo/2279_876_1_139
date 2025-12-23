package com.example.demo.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class CompletedCourse {

    private String code;
    private String grade;
    private Double credits;

    public CompletedCourse() {}

    public CompletedCourse(String code, String grade, Double credits) {
        this.code = code;
        this.grade = grade;
        this.credits = credits;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public Double getCredits() { return credits; }
    public void setCredits(Double credits) { this.credits = credits; }
}
