package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;

public interface AuthService {

    String register(AuthRequest request);

    AuthResponse login(AuthRequest request);
}
