package com.example.demo.entity;

public class TransferEvaluation {

    private Long id;
    private String status;

    public TransferEvaluation() {
    }

    public TransferEvaluation(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
