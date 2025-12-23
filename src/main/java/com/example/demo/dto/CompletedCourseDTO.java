package com.example.demo.dto;

public class CompletedCourseDTO {

    private String courseCode;
    private String grade;
    private Double credits;

    public CompletedCourseDTO() {
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