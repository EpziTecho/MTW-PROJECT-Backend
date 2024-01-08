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
    public List<Booking> findAll() {
        return (List<Booking>) repository.findAll();
    }
    @Override
    public Optional<Booking> findById(Long id) {
        return repository.findById(id);
    }
    
    @Override
    public Booking saveBooking(Booking booking) {
        return repository.save(booking);
    }
    @Override
    public List<Booking> findByCompany(String company) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCompany'");
    }
    @Override
    public void deleteBooking(Long idBooking) {
        repository.deleteById(idBooking);
    }
}
