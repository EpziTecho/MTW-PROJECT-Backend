package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Driver;

public interface BookingService {

    public abstract List<Booking> findAll();

    public abstract Optional<Booking> findById(Long id);

    public abstract List<Booking> findByBillId(Long idBill);

    public abstract Booking saveBooking(Booking booking);

    public abstract void deleteBooking(Long idBooking);

    public abstract List<Booking> listBookingsByParams(Long idBooking, String applicant, Long idCompany,
            Long idPassenger, Long idDriver, Long idCurrency);

    Booking updateDriverPaymentStatus(Long idBooking, Boolean driverPaymentStatus);

    Booking updateClientPaymentStatus(Long idBooking, Boolean clientPaymentStatus);

    List<Booking> findBookingsWithoutBill();

    List<Booking> findBookingsByDriverAndPageable(Long idDriver, Pageable pageable);

    Optional<Booking> findByDriverAndStatusIn(Driver driver, List<String> statuses);
}
