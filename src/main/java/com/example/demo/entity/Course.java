package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "courses",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"university_id", "courseCode"}
    )
)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "university_id")
    private University university;

    @Column(nullable = false)
    private String courseCode;

    private String courseName;
    private Integer creditHours;
    private String description;
    private String department;

    @Column(nullable = false)
    private Boolean active = true;

    // Default constructor
    public Course() {}

    // Parameterized constructor
    public Course(University university, String courseCode, String courseName,
                  Integer creditHours, String department) {
        this.university = university;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.department = department;
        this.active = true;
    }

    
}
