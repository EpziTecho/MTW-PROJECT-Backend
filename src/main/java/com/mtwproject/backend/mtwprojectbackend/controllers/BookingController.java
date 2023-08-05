package com.mtwproject.backend.mtwprojectbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.services.BookingService;

@RestController
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @GetMapping("/services")
    public List<Booking> list(){
        return bookingService.findAll();
    }
    
}
