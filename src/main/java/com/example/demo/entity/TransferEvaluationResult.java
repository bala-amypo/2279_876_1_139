package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "transfer_evaluation_results")
public class TransferEvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private TransferRequest transferRequest;

    private Double totalTransferableCredits;

    @ElementCollection
    private List<String> acceptedCourses;

    @ElementCollection
    private List<String> missingRequirements;

    private String remarks;

    public TransferEvaluationResult() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TransferRequest getTransferRequest() { return transferRequest; }
    public void setTransferRequest(TransferRequest transferRequest) {
        this.transferRequest = transferRequest;
    }

    public Double getTotalTransferableCredits() { return totalTransferableCredits; }
    public void setTotalTransferableCredits(Double totalTransferableCredits) {
        this.totalTransferableCredits = totalTransferableCredits;
    }

    public List<String> getAcceptedCourses() { return acceptedCourses; }
    public void setAcceptedCourses(List<String> acceptedCourses) {
        this.acceptedCourses = acceptedCourses;
    }

    public List<String> getMissingRequirements() { return missingRequirements; }
    public void setMissingRequirements(List<String> missingRequirements) {
        this.missingRequirements = missingRequirements;
    }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
