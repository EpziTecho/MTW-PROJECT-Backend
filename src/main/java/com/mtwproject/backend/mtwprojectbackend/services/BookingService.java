package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;

public interface BookingService {
public abstract List<Booking> findAll();
public abstract Optional<Booking> findById(Long id);
public abstract List<Booking> findByCompany(String company);
public abstract Booking saveBooking(Booking booking);
public abstract void deleteBooking(Long idBooking);

}
