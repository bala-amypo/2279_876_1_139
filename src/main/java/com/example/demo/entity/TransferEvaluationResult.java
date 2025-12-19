package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferEvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean eligibleForTransfer;

    private String remarks;

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

    // 🔹 Alias method to fix compilation error
    public void setNotes(String notes) {
        this.remarks = notes;
    }
}
