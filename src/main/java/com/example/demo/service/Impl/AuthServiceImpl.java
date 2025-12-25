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
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        // TEMP values (later DB-la irundhu varum)
        Long userId = 1L;
        Set<String> roles = Set.of("USER");

        String token = jwtTokenProvider.createToken(
                userId,
                request.getEmail(),
                roles
        );

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
