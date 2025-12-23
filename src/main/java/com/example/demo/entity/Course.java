package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private String title;

    @Column(nullable = false)
    private Double credits;

    public Course() {}

    public Course(String code, String title, Double credits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Double getCredits() { return credits; }
    public void setCredits(Double credits) { this.credits = credits; }
}
