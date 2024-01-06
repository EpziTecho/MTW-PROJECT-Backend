package com.mtwproject.backend.mtwprojectbackend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.services.BookingService;

import lombok.extern.apachecommons.CommonsLog;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/booking") 
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<Booking>> bookingList(){
       List<Booking> bookingList = bookingService.findAll();
       return ResponseEntity.ok(bookingList);
    }


    //insertar nueva reserva
   @PostMapping
   @ResponseBody
   public ResponseEntity<?> saveBooking(@RequestBody Booking booking){
    HashMap<String,Object> message = new HashMap<>();
    try {
        Booking bookingCreated = bookingService.saveBooking(booking);
        message.put("status", "200");
        message.put("message", "La reserva se ha creado correctamente");
        message.put("booking", bookingCreated);
        return ResponseEntity.ok(message);
    } catch (Exception e) {
        message.put("status", "500");
        message.put("message", "Error al crear la reserva");
        message.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

   


   }
   
    
    
}
