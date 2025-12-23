package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transfer_evaluations")
public class TransferEvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sourceCourseId;
    private Long targetCourseId;

    private Double overlapPercentage;
    private Boolean isEligibleForTransfer;
    private String notes;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSourceCourseId() { return sourceCourseId; }
    public void setSourceCourseId(Long sourceCourseId) { this.sourceCourseId = sourceCourseId; }

    public Long getTargetCourseId() { return targetCourseId; }
    public void setTargetCourseId(Long targetCourseId) { this.targetCourseId = targetCourseId; }

    public Double getOverlapPercentage() { return overlapPercentage; }
    public void setOverlapPercentage(Double overlapPercentage) { this.overlapPercentage = overlapPercentage; }

    public Boolean getIsEligibleForTransfer() { return isEligibleForTransfer; }
    public void setIsEligibleForTransfer(Boolean eligible) {
        this.isEligibleForTransfer = eligible;
    }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}


package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_topics")
public class CourseContentTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topicName;
    private Double weightPercentage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

    public Double getWeightPercentage() { return weightPercentage; }
    public void setWeightPercentage(Double weightPercentage) { this.weightPercentage = weightPercentage; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
