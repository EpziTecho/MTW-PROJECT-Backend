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

    // Listar todas las reservas
    @GetMapping({ "", "/" })
    public ResponseEntity<?> bookingList() {
        HashMap<String, Object> message = new HashMap<>();
        try {
            List<Booking> bookingList = bookingService.findAll();
            if (bookingList.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No se encontraron reservas");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Se encontraron reservas");
            message.put("data", bookingList);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al buscar las reservas");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Buscar reserva por id
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> findBookingById(@PathVariable("id") Long idBooking) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Booking> booking = bookingService.findById(idBooking);
            if (booking.isEmpty()) {
                message.put("status", "404");
                message.put("message", "La reserva no existe");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "La reserva se ha encontrado correctamente");
            message.put("data", booking);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al buscar la reserva");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // insertar nueva reserva
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveBooking(@RequestBody Booking booking) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Booking bookingCreated = bookingService.saveBooking(booking);
            message.put("status", "200");
            message.put("message", "La reserva N° " + bookingCreated.getIdBooking() + " se ha creado correctamente");
            message.put("data", bookingCreated);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al crear la reserva");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

    }

    // Eliminar reserva
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

    // Actualizar reserva
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
            message.put("data", bookingUpdated);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al actualizar la reserva");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Listar reservas por parametros : idBooking, applicant, idCompany,
    // idPassenger, idDriver
    @GetMapping("filterBookingsByParams")
    @ResponseBody
    public ResponseEntity<?> filterBookingsByParams(
            @RequestParam(name = "idBooking", required = false, defaultValue = "") Long idBooking,
            @RequestParam(name = "applicant", required = false, defaultValue = "") String applicant,
            @RequestParam(name = "idCompany", required = false, defaultValue = "") Long idCompany,
            @RequestParam(name = "idPassenger", required = false, defaultValue = "") Long idPassenger,
            @RequestParam(name = "idDriver", required = false, defaultValue = "") Long idDriver,
            @RequestParam(name = "idCurrency", required = false, defaultValue = "") Long idCurrency) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            List<Booking> bookingList = bookingService.listBookingsByParams(idBooking, applicant, idCompany,
                    idPassenger, idDriver, idCurrency);
            if (bookingList.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No se encontraron reservas");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Se encontraron reservas");
            message.put("data", bookingList);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al buscar las reservas");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Actualizar el estado de la reserva a FINALIZED_STATUS
    @PutMapping("/finalize")
    @ResponseBody
    public ResponseEntity<?> finalizeBooking(@RequestBody Booking booking) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Booking> bookingOptional = bookingService.findById(booking.getIdBooking());
            if (bookingOptional.isEmpty()) {
                message.put("status", "404");
                message.put("message", "La reserva no existe");
                return ResponseEntity.ok(message);
            }
            Booking bookingFound = bookingOptional.get();
            bookingFound.setStatus(BookingServiceImpl.FINALIZED_STATUS);
            bookingService.saveBooking(bookingFound);
            message.put("status", "200");
            message.put("message", "La reserva se ha finalizado correctamente");
            message.put("data", bookingFound);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al finalizar la reserva");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Actualizar el estado de la reserva a IN_PROCESS_STATUS
    @PutMapping("/inProcess")
    @ResponseBody
    public ResponseEntity<?> setStatusToEnProcessBooking(@RequestBody Booking booking) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Booking> bookingOptional = bookingService.findById(booking.getIdBooking());
            if (bookingOptional.isEmpty()) {
                message.put("status", "404");
                message.put("message", "La reserva no existe");
                return ResponseEntity.ok(message);
            }
            Booking bookingFound = bookingOptional.get();
            bookingFound.setStatus(BookingServiceImpl.IN_PROCESS_STATUS);
            bookingService.saveBooking(bookingFound);
            message.put("status", "200");
            message.put("message", "La reserva se ha finalizado correctamente");
            message.put("data", bookingFound);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al finalizar la reserva");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Actualizar el paymentStatus del conductor de la reserva
    @PutMapping("/updateDriverPaymentStatus")
    @ResponseBody
    public ResponseEntity<?> updateDriverPaymentStatus(@RequestBody Booking booking) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Booking> bookingOptional = bookingService.findById(booking.getIdBooking());
            if (bookingOptional.isEmpty()) {
                message.put("status", "404");
                message.put("message", "La reserva no existe");
                return ResponseEntity.ok(message);
            }
            Booking bookingFound = bookingOptional.get();
            bookingFound.setDriverPaymentStatus(booking.getDriverPaymentStatus());
            bookingService.saveBooking(bookingFound);
            message.put("status", "200");
            message.put("message", "El estado de pago del conductor se ha actualizado correctamente");
            message.put("data", bookingFound);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al actualizar el estado de pago del conductor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Actualizar el paymentStatus del cliente de la reserva
    @PutMapping("/updateClientPaymentStatus")
    @ResponseBody
    public ResponseEntity<?> updateClientPaymentStatus(@RequestBody Booking booking) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Booking> bookingOptional = bookingService.findById(booking.getIdBooking());
            if (bookingOptional.isEmpty()) {
                message.put("status", "404");
                message.put("message", "La reserva no existe");
                return ResponseEntity.ok(message);
            }
            Booking bookingFound = bookingOptional.get();
            bookingFound.setClientPaymentStatus(booking.getClientPaymentStatus());
            bookingService.saveBooking(bookingFound);
            message.put("status", "200");
            message.put("message", "El estado de pago del cliente se ha actualizado correctamente");
            message.put("data", bookingFound);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al actualizar el estado de pago del cliente");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // listar reservas que no tienen facturas asociadas

    @GetMapping("noBills")
    @ResponseBody
    public ResponseEntity<?> listBookingsWithoutBills() {
        HashMap<String, Object> message = new HashMap<>();
        try {
            List<Booking> bookingList = bookingService.findBookingsWithoutBill();
            if (bookingList.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No se encontraron reservas sin facturas");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Se encontraron reservas sin facturas");
            message.put("data", bookingList);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al buscar las reservas sin facturas");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

}
