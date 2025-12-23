package com.example.demo.dto;

import java.util.List;

public class TransferEvaluationResponse {

    private Double totalTransferableCredits;
    private List<String> acceptedMappings;
    private List<String> missingRequirements;
    private String status;
    private String remarks;

    public TransferEvaluationResponse() {
    }

    public Double getTotalTransferableCredits() {
        return totalTransferableCredits;
    }

    public void setTotalTransferableCredits(Double totalTransferableCredits) {
        this.totalTransferableCredits = totalTransferableCredits;
    }

    public List<String> getAcceptedMappings() {
        return acceptedMappings;
    }

    public void setAcceptedMappings(List<String> acceptedMappings) {
        this.acceptedMappings = acceptedMappings;
    }

    public List<String> getMissingRequirements() {
        return missingRequirements;
    }

    public void setMissingRequirements(List<String> missingRequirements) {
        this.missingRequirements = missingRequirements;
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
}