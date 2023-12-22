package com.mtwproject.backend.mtwprojectbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Passenger;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {
    

}