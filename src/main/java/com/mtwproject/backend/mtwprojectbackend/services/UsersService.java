package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Users;

public interface UsersService {
    List <Users> findAll();

    Optional<Users> findById(Long id);

    Users save(Users user);

    Optional<Users> update (Users user , Long id);

    void remove (Long id);

    Optional<Users> findByUsername(String username);
}
