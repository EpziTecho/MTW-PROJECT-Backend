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
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.services.BookingService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/booking") 
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @GetMapping   
    public List<Booking> list(){
        return bookingService.findAll();
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable  Long id){
        Optional <Booking> bookingOptional= bookingService.findById(id);
        if(bookingOptional.isPresent()){
            return ResponseEntity.ok(bookingOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
        
    }
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Booking booking){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.save(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Booking booking, @PathVariable Long id){
        Optional < Booking> bookingOptional= bookingService.update(booking, id);
        if(bookingOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional <Booking> bookingOptional= bookingService.findById(id);
        if(bookingOptional.isPresent()){
            bookingService.remove(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    

    // Cambiar el estado de la reserva a "En proceso"
    @PutMapping("/status/enProceso")
    public ResponseEntity<?> updateStatustoenProceso(@RequestBody Booking booking) {
        String result = bookingService.updateStatusToEnProceso(booking.getIdBooking());
        
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", result);
    
        if (result.equals("La reserva no existe")) {
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    
        return ResponseEntity.ok(response);
    }

    // Cambiar el estado de la reserva a "Finalizado"
    @PutMapping("/status/finalizado")
    public ResponseEntity<?> updateStatustoFinalizado(@RequestBody Booking booking) {
        String result = bookingService.updateStatusToFinalizado(booking.getIdBooking());
        
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", result);
    
        if (result.equals("La reserva no existe")) {
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    
        return ResponseEntity.ok(response);
    }
     
    
}
