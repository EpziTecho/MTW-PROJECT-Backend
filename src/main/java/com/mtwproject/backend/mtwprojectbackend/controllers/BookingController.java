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
import com.mtwproject.backend.mtwprojectbackend.services.BookingServiceImpl;

import org.springframework.web.bind.annotation.RequestParam;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/booking") 
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    //Listar todas las reservas
    @GetMapping({"", "/"})
    public ResponseEntity<List<Booking>> bookingList(){
       List<Booking> bookingList = bookingService.findAll();
       return ResponseEntity.ok(bookingList);
    }

    //Buscar reserva por id
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> findBookingById(@PathVariable("id") Long idBooking){
        HashMap<String,Object> message = new HashMap<>();
        try {
            Optional<Booking> booking = bookingService.findById(idBooking);
            if (booking.isEmpty()) {
                message.put("status", "404");
                message.put("message", "La reserva no existe");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "La reserva se ha encontrado correctamente");
            message.put("booking", booking);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al buscar la reserva");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
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
   
   //Eliminar reserva
   @DeleteMapping("/{id}")
   @ResponseBody
   public ResponseEntity<?> deleteBooking(@PathVariable("id") Long idBooking) {
       HashMap<String, Object> message = new HashMap<>();
       try {
           Optional<Booking> booking = bookingService.findById(idBooking);
           if (booking.isEmpty()) {
               message.put("status", "404");
               message.put("message", "La reserva no existe");
               return ResponseEntity.ok(message);
           }
           bookingService.deleteBooking(idBooking);
           message.put("status", "200");
           message.put("message", "La reserva se ha eliminado correctamente");
           return ResponseEntity.ok(message);
       } catch (Exception e) {
           message.put("status", "500");
           message.put("message", "Se produjo un error al eliminar la reserva");
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
       }
   }
    
    //Actualizar reserva
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateBooking(@RequestBody Booking booking) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Booking> bookingFound = bookingService.findById(booking.getIdBooking());
            if (bookingFound.isEmpty()) {
                message.put("status", "404");
                message.put("message", "La reserva no existe");
                return ResponseEntity.ok(message);
            }
            Booking bookingUpdated = bookingService.saveBooking(booking);
            message.put("status", "200");
            message.put("message", "La reserva se ha actualizado correctamente");
            message.put("booking", bookingUpdated);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al actualizar la reserva");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    //Listar reservas por parametros : idBooking, applicant, idCompany, idPassenger, idDriver
    @GetMapping("filterBookingsByParams")
    @ResponseBody
    public ResponseEntity<?> filterBookingsByParams(@RequestParam(name = "idBooking", required = false, defaultValue="") Long idBooking,
    @RequestParam(name = "applicant", required = false, defaultValue="") String applicant,
    @RequestParam(name = "idCompany", required = false, defaultValue="") Long idCompany,
    @RequestParam(name = "idPassenger", required = false, defaultValue="") Long idPassenger,
    @RequestParam(name = "idDriver", required = false, defaultValue="") Long idDriver){
        HashMap<String,Object> message = new HashMap<>();
        try {
            List<Booking> bookingList = bookingService.listBookingsByParams(idBooking, applicant, idCompany, idPassenger, idDriver);
            if (bookingList.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No se encontraron reservas");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Se encontraron reservas");
            message.put("bookingList", bookingList);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al buscar las reservas");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
    
    @PutMapping("/finalize/{id}")
    @ResponseBody
    public ResponseEntity<?> finalizeBooking(@PathVariable("id") Long idBooking) {
    HashMap<String, Object> message = new HashMap<>();
    try {
        Optional<Booking> bookingOptional = bookingService.findById(idBooking);
        if (bookingOptional.isEmpty()) {
            message.put("status", "404");
            message.put("message", "La reserva no existe");
            return ResponseEntity.ok(message);
        }
        Booking booking = bookingOptional.get();
        booking.setStatus(BookingServiceImpl.FINALIZED_STATUS);
        bookingService.saveBooking(booking);
        message.put("status", "200");
        message.put("message", "La reserva se ha finalizado correctamente");
        message.put("booking", booking);
        return ResponseEntity.ok(message);
        } catch (Exception e) {
        message.put("status", "500");
        message.put("message", "Se produjo un error al finalizar la reserva");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping("/inProcess/{id}")
    @ResponseBody
    public ResponseEntity<?> setStatusToEnProcessBooking(@PathVariable("id") Long idBooking) {
    HashMap<String, Object> message = new HashMap<>();
    try {
        Optional<Booking> bookingOptional = bookingService.findById(idBooking);
        if (bookingOptional.isEmpty()) {
            message.put("status", "404");
            message.put("message", "La reserva no existe");
            return ResponseEntity.ok(message);
        }
        Booking booking = bookingOptional.get();
        booking.setStatus(BookingServiceImpl.IN_PROCESS_STATUS);
        bookingService.saveBooking(booking);
        message.put("status", "200");
        message.put("message", "La reserva se ha finalizado correctamente");
        message.put("booking", booking);
        return ResponseEntity.ok(message);
        } catch (Exception e) {
        message.put("status", "500");
        message.put("message", "Se produjo un error al finalizar la reserva");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
   
    
}
