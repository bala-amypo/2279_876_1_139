// package com.example.demo.service;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;

// public interface AuthService {
//     AuthResponse login(AuthRequest request);
// }


package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RegisterResponse;

public interface AuthService {

    AuthResponse login(AuthRequest request);

    RegisterResponse register(RegisterRequest request);
}
