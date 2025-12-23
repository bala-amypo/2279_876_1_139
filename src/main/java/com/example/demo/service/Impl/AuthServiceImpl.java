package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    UserRepository userRepo;
    JwtTokenProvider jwtTokenProvider;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public AuthResponse login(AuthRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtTokenProvider.createToken(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );

        return new AuthResponse(token, user.getEmail(), user.getRoles().iterator().next());
    }
}