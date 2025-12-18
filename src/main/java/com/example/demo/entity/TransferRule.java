package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transfer_rules")
public class TransferRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_university_id")
    private University sourceUniversity;

    @ManyToOne
    @JoinColumn(name = "target_university_id")
    private University targetUniversity;

    private Double minimumOverlapPercentage;
    private Integer creditHourTolerance;

    @Column(nullable = false)
    private Boolean active = true;

    
    public TransferRule() {
    }

    
    public TransferRule(University sourceUniversity, University targetUniversity,
                        Double minimumOverlapPercentage, Integer creditHourTolerance) {
        this.sourceUniversity = sourceUniversity;
        this.targetUniversity = targetUniversity;
        this.minimumOverlapPercentage = minimumOverlapPercentage;
        this.creditHourTolerance = creditHourTolerance;
        this.active = true;
    }

    
}
