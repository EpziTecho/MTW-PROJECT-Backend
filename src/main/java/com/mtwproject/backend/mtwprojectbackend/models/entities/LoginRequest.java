package com.mtwproject.backend.mtwprojectbackend.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoginRequest {

    private String username;
    private String password;


    
}
