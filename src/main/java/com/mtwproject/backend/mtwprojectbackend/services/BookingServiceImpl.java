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
                bookingDb.setIdDistritPickUp(booking.getIdDistritPickUp());
                bookingDb.setDestination(booking.getDestination());
                bookingDb.setIdDistritDestination(booking.getIdDistritDestination());
                bookingDb.setNotes(booking.getNotes());
                bookingDb.setPrice(booking.getPrice());
                bookingDb.setIdDriver(booking.getIdDriver());
                bookingDb.setStatus(booking.getStatus());
                bookingDb.setIdBill(booking.getIdBill());
                bookingOptional=repository.save(bookingDb);
                
            }
            return Optional.ofNullable(bookingOptional);
    }
        

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
