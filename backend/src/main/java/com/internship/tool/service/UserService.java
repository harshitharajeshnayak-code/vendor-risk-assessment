package com.internship.tool.service;

import com.internship.tool.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Login user and return dummy token
    public String loginUser(String email, String password) {

        return "sample-jwt-token";
    }

    // Register user
    public void registerUser(RegisterRequest request) {

        System.out.println("User registered: " + request.getEmail());
    }
}