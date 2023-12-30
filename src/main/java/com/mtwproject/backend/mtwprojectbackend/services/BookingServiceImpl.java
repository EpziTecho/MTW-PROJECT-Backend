package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.repositories.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository repository;
    // Constante para el manejo de estados
    private static final String PENDING_DRIVER_ASSIGNED_STATUS = "Pendiente (conductor asignado)";
    private static final String RESERVE_STATUS = "Reserva";
    private static final String IN_PROCESS_STATUS = "En proceso";
    private static final String FINALIZED_STATUS = "Finalizado";

    @Override
    @Transactional(readOnly = true)
    public List <Booking > findAll() {
        return (List <Booking>) repository.findAll();
    }

    @Override
    public Optional<Booking> findById(Long id) {
      
       return repository.findById(id);
    }

    @Override
    @Transactional
    public Booking save(Booking booking) {
        // Cambiar el estado a "Reserva" al crear un nuevo registro
        booking.setStatus(RESERVE_STATUS);
       return repository.save(booking);
    }

    @Override
    @Transactional
    public Optional<Booking> update(Booking booking, Long id) {
        Optional <Booking> o = repository.findById(id);
         Booking bookingOptional=null;
            if(o.isPresent()) {
                Booking bookingDb=o.orElseThrow();
                bookingDb.setDate(booking.getDate());
                bookingDb.setTime(booking.getTime());
                bookingDb.setIdCompany(booking.getIdCompany());
                bookingDb.setApplicant(booking.getApplicant());
                bookingDb.setIdArea(booking.getIdArea());
                bookingDb.setIdPassenger(booking.getIdPassenger());
                bookingDb.setPickUp(booking.getPickUp());
                bookingDb.setIdUbigeoPickUp(booking.getIdUbigeoPickUp());
                bookingDb.setDestination(booking.getDestination());
                bookingDb.setIdUbigeoDestination(booking.getIdUbigeoDestination());
                bookingDb.setNotes(booking.getNotes());
                bookingDb.setIdCurrency(booking.getIdCurrency());
                bookingDb.setPrice(booking.getPrice());
                bookingDb.setIdDriver(booking.getIdDriver());
                bookingDb.setIdBill(booking.getIdBill());
            // Cambiar el estado a "Pendiente (conductor asignado)" si se actualiza el idBill
            if (booking.getIdDriver() != null && !booking.getIdDriver().equals(bookingDb.getIdBill())) {
                bookingDb.setStatus(PENDING_DRIVER_ASSIGNED_STATUS);
            }

            bookingOptional = repository.save(bookingDb);
        }
        return Optional.ofNullable(bookingOptional);
    }

    @Override
    @Transactional
    public String updateStatusToEnProceso(Long id) {
            return repository.findById(id).map(booking -> {
            booking.setStatus(IN_PROCESS_STATUS);
            repository.save(booking);
            return "La reserva se actualizó correctamente";
        }).orElse("La reserva no existe");
    }

    @Override
    @Transactional
    public String updateStatusToFinalizado(Long id) {
        return repository.findById(id).map(booking -> {
            booking.setStatus(FINALIZED_STATUS);
            repository.save(booking);
            return "La reserva se actualizó correctamente";
        }).orElse("La reserva no existe");
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
