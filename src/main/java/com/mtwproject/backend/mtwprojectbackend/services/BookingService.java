package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;


import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;

public interface BookingService {
    List<Booking> findAll();
    
    Optional<Booking> findById(Long id);

    Booking save(Booking booking);

    Optional<Booking> update (Booking booking , Long id);

    void remove (Long id);
    
    String updateStatusToEnProceso(Long id);
    String updateStatusToFinalizado(Long id);
}
