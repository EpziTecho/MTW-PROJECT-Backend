package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mtwproject.backend.mtwprojectbackend.models.entities.LoginRequest;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Users;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsersService usersService;

    @Override
    public ResponseEntity<String> authenticateUser(LoginRequest loginRequest) {
        Optional<Users> userOptional = usersService.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok("Login exitoso");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}