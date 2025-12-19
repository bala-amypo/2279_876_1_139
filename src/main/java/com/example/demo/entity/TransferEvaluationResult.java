package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class TransferEvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalTransferableCredits;

    private String status;

    private String remarks;

    private Timestamp evaluatedAt;

    private boolean eligibleForTransfer;

    public TransferEvaluationResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalTransferableCredits() {
        return totalTransferableCredits;
    }

    public void setTotalTransferableCredits(Double totalTransferableCredits) {
        this.totalTransferableCredits = totalTransferableCredits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getEvaluatedAt() {
        return evaluatedAt;
    }

    public void setEvaluatedAt(Timestamp evaluatedAt) {
        this.evaluatedAt = evaluatedAt;
    }

    public boolean isEligibleForTransfer() {
        return eligibleForTransfer;
    }

    public void setEligibleForTransfer(boolean eligibleForTransfer) {
        this.eligibleForTransfer = eligibleForTransfer;
    }
}
