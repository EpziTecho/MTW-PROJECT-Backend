package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Passenger;

public interface PassengerService {
    List <Passenger> findAll();

    Optional<Passenger> findById(Long id);

    Passenger save(Passenger passenger);

    Optional<Passenger> update (Passenger passenger , Long id);

    void remove (Long id);
    
    String deactivatePassenger(Long id);
    
}
