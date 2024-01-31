package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mtwproject.backend.mtwprojectbackend.models.entities.LoginRequest;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Users;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsersService usersService;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Optional<Users> userOptional = usersService.findByUsername(loginRequest.getUsername());
        HashMap<String, String> response = new HashMap<>();
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(user); // Devolverá el objeto Users en el cuerpo de la respuesta
            } else {
                response.put("message", "Credenciales inválidas");
                return ResponseEntity.ok(response);
            }
        } else {
            response.put("message", "Credenciales inválidas");
            return ResponseEntity.ok(response);
        }
    }
}