package com.mtwproject.backend.mtwprojectbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Users;

public interface UsersRepository  extends CrudRepository<Users, Long>{
    
}
