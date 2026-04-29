package com.internship.tool.config;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // Refresh token
    public String refreshToken(String oldToken) {

        return "refreshed-jwt-token";
    }
}