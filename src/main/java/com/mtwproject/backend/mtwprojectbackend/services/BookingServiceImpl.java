package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.repositories.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository repository;
    // Constante para el manejo de estados
    private static final String RESERVE_STATUS = "En Reserva";
    private static final String PENDING_DRIVER_ASSIGNED_STATUS = "Pendiente";
    public static final String IN_PROCESS_STATUS = "En Proceso";
    public static final String FINALIZED_STATUS = "Finalizado";

    @Override
    public List<Booking> findAll() {
        return (List<Booking>) repository.findAll();
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        // Si la reserva es nueva (idBooking es nulo), establecer el estado a
        // RESERVE_STATUS
        if (booking.getIdBooking() == null) {
            booking.setStatus(RESERVE_STATUS);
        } else {
            // Si la reserva ya existe, comprobar si idDriver no es nulo y el estado actual
            // es RESERVE_STATUS
            if (booking.getDriver() != null && booking.getDriver().getIdDriver() != null
                    && RESERVE_STATUS.equals(booking.getStatus())) {
                // Si idDriver no es nulo y el estado actual es RESERVE_STATUS, establecer el
                // estado a PENDING_DRIVER_ASSIGNED_STATUS
                booking.setStatus(PENDING_DRIVER_ASSIGNED_STATUS);
            }
            // Si idDriver es nulo o el estado actual no es RESERVE_STATUS, no cambiar el
            // estado
        }
        return repository.save(booking);
    }

    @Override
    public void deleteBooking(Long idBooking) {
        repository.deleteById(idBooking);
    }

    @Override
    public List<Booking> listBookingsByParams(Long idBooking, String applicant, Long idCompany, Long idPassenger,
            Long idDriver) {
        return repository.listBookingsByParams(idBooking, applicant, idCompany, idPassenger, idDriver);
    }

    @Override
    public Booking updateDriverPaymentStatus(Long idBooking, Boolean driverPaymentStatus) {
        Optional<Booking> bookingOptional = repository.findById(idBooking);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            booking.setDriverPaymentStatus(driverPaymentStatus);
            return repository.save(booking);
        } else {
            return null;
        }
    }

    @Override
    public Booking updateClientPaymentStatus(Long idBooking, Boolean clientPaymentStatus) {
        Optional<Booking> bookingOptional = repository.findById(idBooking);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            booking.setClientPaymentStatus(clientPaymentStatus);
            return repository.save(booking);
        } else {
            return null;
        }
    }
}
