package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;

public interface BookingService {
   public abstract List<Booking> findAll();

}
