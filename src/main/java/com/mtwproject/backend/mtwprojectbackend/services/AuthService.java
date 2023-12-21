package com.mtwproject.backend.mtwprojectbackend.services;

import org.springframework.http.ResponseEntity;

import com.mtwproject.backend.mtwprojectbackend.models.entities.LoginRequest;

public interface AuthService {
    ResponseEntity<String> authenticateUser(LoginRequest loginRequest);
}