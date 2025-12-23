package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_evaluation_results")
public class TransferEvaluationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_course_id", nullable = false)
    private Course sourceCourse;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_course_id", nullable = false)
    private Course targetCourse;
    
    @Column(nullable = false)
    private Boolean isEligibleForTransfer;
    
    @Column
    private Double overlapPercentage;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @Column(nullable = false)
    private LocalDateTime evaluationDate = LocalDateTime.now();

    public TransferEvaluationResult() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Course getSourceCourse() { return sourceCourse; }
    public void setSourceCourse(Course sourceCourse) { this.sourceCourse = sourceCourse; }
    
    public Course getTargetCourse() { return targetCourse; }
    public void setTargetCourse(Course targetCourse) { this.targetCourse = targetCourse; }
    
    public Boolean getIsEligibleForTransfer() { return isEligibleForTransfer; }
    public void setIsEligibleForTransfer(Boolean isEligibleForTransfer) { this.isEligibleForTransfer = isEligibleForTransfer; }
    
    public Double getOverlapPercentage() { return overlapPercentage; }
    public void setOverlapPercentage(Double overlapPercentage) { this.overlapPercentage = overlapPercentage; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getEvaluationDate() { return evaluationDate; }
    public void setEvaluationDate(LocalDateTime evaluationDate) { this.evaluationDate = evaluationDate; }
}