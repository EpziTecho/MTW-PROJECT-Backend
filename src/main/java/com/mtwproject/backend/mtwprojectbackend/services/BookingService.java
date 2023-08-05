package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;

public interface BookingService {
    List<Booking> findAll();
    
}
