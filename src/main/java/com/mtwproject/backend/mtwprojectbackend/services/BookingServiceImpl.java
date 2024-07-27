package com.mtwproject.backend.mtwprojectbackend.services;

import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Driver;
import com.mtwproject.backend.mtwprojectbackend.repositories.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository repository;
    // Constante para el manejo de estados
    public static final String RESERVE_STATUS = "En Reserva";
    public static final String PENDING_DRIVER_ASSIGNED_STATUS = "Pendiente";
    public static final String AWAITING_STATUS = "En Espera";
    public static final String IN_PROCESS_STATUS = "En Proceso";
    public static final String FINALIZED_STATUS = "Finalizado";

    @Override
    public List<Booking> findAll() {
        return (List<Booking>) repository.findAllByOrderByIdBookingDesc();
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        if (booking.getIdBooking() == null) {
            // Si la reserva es nueva y ya tiene un conductor asignado, establecer el estado
            // a PENDING_DRIVER_ASSIGNED_STATUS
            if (booking.getDriver() != null && booking.getDriver().getIdDriver() != null) {
                booking.setStatus(PENDING_DRIVER_ASSIGNED_STATUS);
            } else {
                // Si la reserva es nueva y no tiene conductor asignado, establecer el estado a
                // RESERVE_STATUS
                booking.setStatus(RESERVE_STATUS);
            }
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
            Long idDriver, Long idCurrency) {
        return repository.listBookingsByParams(idBooking, applicant, idCompany, idPassenger, idDriver, idCurrency);
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

    @Override
    public List<Booking> findByBillId(Long idBill) {
        return repository.findByBillId(idBill);
    }

    @Override
    public List<Booking> findBookingsWithoutBill() {
        return repository.findBookingsWithoutBill();
    }

    @Override
    public List<Booking> findBookingsByDriverAndPageable(Long idDriver, Pageable pageable) {
        Time timeWithTolerance = getCurrentTimeWithTolerance();
        return repository.findBookingsByDriverTimeWithToleranceAndPageable(idDriver, timeWithTolerance,
                pageable);
    }

    public Time getCurrentTimeWithTolerance() {
        LocalTime now = LocalTime.now();
        LocalTime timeWithTolerance = now.minus(30, ChronoUnit.MINUTES);
        return Time.valueOf(timeWithTolerance);
    }

    @Override
    public Optional<Booking> findByDriverAndStatusIn(Driver driver, List<String> statuses) {
        return repository.findByDriverAndStatusIn(driver, statuses);
    }

    @Override
    public List<Booking> findByDriverAndStatusInOrderByDateDescTimeDesc(Driver driver, List<String> statuses,
            Pageable pageable) {
        return repository.findByDriverAndStatusInOrderByDateDescTimeDesc(driver, statuses, pageable);
    }

}
