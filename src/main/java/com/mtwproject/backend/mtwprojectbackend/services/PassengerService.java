package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Passenger;

public interface PassengerService {
    List <Passenger> findAll();
    
}
