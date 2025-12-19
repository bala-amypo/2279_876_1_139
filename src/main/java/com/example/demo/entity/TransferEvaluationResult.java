package com.example.demo.entity;

public class TransferEvaluationResult {
    private Long id;
    private String result;

    public TransferEvaluationResult() {}

    public TransferEvaluationResult(Long id, String result) {
        this.id = id;
        this.result = result;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
}
