package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transfer_rules")
public class TransferRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_university_id", nullable = false)
    private University sourceUniversity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_university_id", nullable = false)
    private University targetUniversity;
    
    @Column(nullable = false)
    private Double minimumOverlapPercentage;
    
    @Column(nullable = false)
    private Integer creditHourTolerance = 0;
    
    @Column(nullable = false)
    private boolean active = true;

    public TransferRule() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public University getSourceUniversity() { return sourceUniversity; }
    public void setSourceUniversity(University sourceUniversity) { this.sourceUniversity = sourceUniversity; }
    
    public University getTargetUniversity() { return targetUniversity; }
    public void setTargetUniversity(University targetUniversity) { this.targetUniversity = targetUniversity; }
    
    public Double getMinimumOverlapPercentage() { return minimumOverlapPercentage; }
    public void setMinimumOverlapPercentage(Double minimumOverlapPercentage) { this.minimumOverlapPercentage = minimumOverlapPercentage; }
    
    public Integer getCreditHourTolerance() { return creditHourTolerance; }
    public void setCreditHourTolerance(Integer creditHourTolerance) { this.creditHourTolerance = creditHourTolerance; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}