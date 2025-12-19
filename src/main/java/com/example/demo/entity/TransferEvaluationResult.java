
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_evaluation_results")
public class TransferEvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_course_id", nullable = false)
    private Course sourceCourse;

    @ManyToOne
    @JoinColumn(name = "target_course_id", nullable = false)
    private Course targetCourse;

    private Double overlapPercentage;

    private Integer creditHourDifference;

    private Boolean isEligibleForTransfer;

    private LocalDateTime evaluatedAt = LocalDateTime.now();

    private String notes;

    public TransferEvaluationResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getOverlapPercentage() {
        return overlapPercentage;
    }

    public void setOverlapPercentage(Double overlapPercentage) {
        this.overlapPercentage = overlapPercentage;
    }

    public Integer getCreditHourDifference() {
        return creditHourDifference;
    }

    public void setCreditHourDifference(Integer creditHourDifference) {
        this.creditHourDifference = creditHourDifference;
    }

    public Boolean getIsEligibleForTransfer() {
        return isEligibleForTransfer;
    }

    public void setIsEligibleForTransfer(Boolean eligibleForTransfer) {
        isEligibleForTransfer = eligibleForTransfer;
    }

    public LocalDateTime getEvaluatedAt() {
        return evaluatedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
