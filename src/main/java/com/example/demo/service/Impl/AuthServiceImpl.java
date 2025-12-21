package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository repo;

    @Override
    public User register(User user) {
        repo.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("User already exists");
        });
        return repo.save(user);
    }

    @Override
    public User login(User user) {
        return repo.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}