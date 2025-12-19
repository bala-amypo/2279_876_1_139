package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class TransferEvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean eligibleForTransfer;

    private String remarks;

    private Timestamp evaluatedAt;

    public TransferEvaluationResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEligibleForTransfer() {
        return eligibleForTransfer;
    }

    public void setEligibleForTransfer(boolean eligibleForTransfer) {
        this.eligibleForTransfer = eligibleForTransfer;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // ✔ Needed by service
    public Timestamp getEvaluatedAt() {
        return evaluatedAt;
    }

    public void setEvaluatedAt(Timestamp evaluatedAt) {
        this.evaluatedAt = evaluatedAt;
    }

    // ✔ Alias for earlier error (safe)
    public void setNotes(String notes) {
        this.remarks = notes;
    }
}
