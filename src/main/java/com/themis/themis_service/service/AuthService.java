package com.themis.themis_service.service;

import com.themis.themis_service.dto.LoginRequest;

public class AuthService {
    public String login(LoginRequest loginRequest) {
        return "Login successful for user: " + loginRequest.getUsername();
    }
}
