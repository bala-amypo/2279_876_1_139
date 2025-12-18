package com.example.transferportal.dto;

public class AuthResponse {

    private String token;

    // Default constructor
    public AuthResponse() {
    }

    // Parameterized constructor
    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
}
