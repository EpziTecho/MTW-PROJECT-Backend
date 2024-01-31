package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;

public interface BookingService {

    public abstract List<Booking> findAll();

    public abstract Optional<Booking> findById(Long id);

    public abstract List<Booking> findByBillId(Long idBill);

    public abstract Booking saveBooking(Booking booking);

    public abstract void deleteBooking(Long idBooking);

    public abstract List<Booking> listBookingsByParams(Long idBooking, String applicant, Long idCompany,
            Long idPassenger, Long idDriver);

    Booking updateDriverPaymentStatus(Long idBooking, Boolean driverPaymentStatus);

    Booking updateClientPaymentStatus(Long idBooking, Boolean clientPaymentStatus);

}
