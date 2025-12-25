// package com.example.demo.service.impl;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.security.JwtTokenProvider;
// import com.example.demo.service.AuthService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;
// import java.util.Set;

// @Service
// public class AuthServiceImpl implements AuthService {

//     UserRepository userRepo;
//     JwtTokenProvider jwtTokenProvider;
//     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//     @Override
//     public AuthResponse login(AuthRequest request) {

//         User user = userRepo.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new RuntimeException("Invalid credentials"));

//         if (!encoder.matches(request.getPassword(), user.getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }

//         String token = jwtTokenProvider.createToken(
//                 user.getId(),
//                 user.getEmail(),
//                 user.getRoles()
//         );

//         return new AuthResponse(token, user.getEmail(), user.getRoles().iterator().next());
//     }
// }




package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RegisterResponse;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;

    public AuthServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        String token = jwtUtil.generateToken(request.getEmail());

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(request.getEmail());
        response.setRole("USER");

        return response;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        RegisterResponse response = new RegisterResponse();
        response.setMessage("User registered successfully");
        response.setEmail(request.getEmail());
        response.setRole("USER");

        return response;
    }
}
