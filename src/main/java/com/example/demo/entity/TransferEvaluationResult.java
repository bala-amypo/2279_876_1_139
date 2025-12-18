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
    @JoinColumn(name = "source_course_id")
    private Course sourceCourse;

    @ManyToOne
    @JoinColumn(name = "target_course_id")
    private Course targetCourse;

    private Double overlapPercentage;
    private Integer creditHourDifference;
    private Boolean isEligibleForTransfer;

    private LocalDateTime evaluatedAt = LocalDateTime.now();
    private String notes;

    
    public TransferEvaluationResult() {}

    
    public TransferEvaluationResult(Course sourceCourse, Course targetCourse,
                                    Double overlapPercentage, Integer creditHourDifference,
                                    Boolean isEligibleForTransfer, String notes) {
        this.sourceCourse = sourceCourse;
        this.targetCourse = targetCourse;
        this.overlapPercentage = overlapPercentage;
        this.creditHourDifference = creditHourDifference;
        this.isEligibleForTransfer = isEligibleForTransfer;
        this.notes = notes;
    }

    
}
