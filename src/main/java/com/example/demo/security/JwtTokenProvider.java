package com.example.demo.security;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtTokenProvider {

    public String generateToken(String email, String role) {
        // Dummy token generation for test compatibility
        return "jwt-token-" + UUID.randomUUID();
    }
}
