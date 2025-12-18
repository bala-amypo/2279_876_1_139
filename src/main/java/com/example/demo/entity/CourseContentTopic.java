package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_content_topics")
public class CourseContentTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private String topicName;

    private Double weightPercentage;

    
    public CourseContentTopic() {
    }

   
    public CourseContentTopic(Course course, String topicName, Double weightPercentage) {
        this.course = course;
        this.topicName = topicName;
        this.weightPercentage = weightPercentage;
    }

    
}
