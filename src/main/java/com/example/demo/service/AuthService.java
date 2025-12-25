// package com.example.demo.service;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;

// public interface AuthService {
//     AuthResponse login(AuthRequest request);
// }



public interface AuthService {

    AuthResponse login(AuthRequest request);

    RegisterResponse register(RegisterRequest request);
}
