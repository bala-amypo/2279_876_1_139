package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepo;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User register(User user) {

        userRepo.findByEmail(user.getEmail())
                .ifPresent(u -> { throw new IllegalArgumentException("Email already exists"); });

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}