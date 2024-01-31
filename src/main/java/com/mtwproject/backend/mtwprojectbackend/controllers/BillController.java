package com.mtwproject.backend.mtwprojectbackend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Bill;
import com.mtwproject.backend.mtwprojectbackend.models.entities.BillAndBookingDTO;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.services.BillService;
import com.mtwproject.backend.mtwprojectbackend.services.BookingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bill")

public class BillController {

    @Autowired
    private BillService service;

    @Autowired
    private BookingService bookingService;

    // Listar todas las facturas
    @GetMapping({ "/", "" })
    public ResponseEntity<?> findAll() {
        HashMap<String, Object> message = new HashMap<>();
        try {
            List<Bill> bills = service.findAll();
            if (bills.isEmpty()) {
                message.put("status", 404);
                message.put("message", "No se encontraron facturas");
                return ResponseEntity.ok(message);
            }
            message.put("status", 200);
            message.put("message", "Facturas encontradas");
            message.put("data", bills);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", 500);
            message.put("message", "Error al buscar facturas");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

    }

    // listar facturas por id
    @GetMapping({ "/{id}" })
    @ResponseBody
    public ResponseEntity<?> findBillById(@PathVariable("id") Long idBills) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Bill> bill = service.findById(idBills);
            if (!bill.isPresent()) {
                message.put("status", 404);
                message.put("message", "No se encontró la factura");
                return ResponseEntity.ok(message);
            }
            message.put("status", 200);
            message.put("message", "Factura encontrada");
            message.put("data", bill);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", 500);
            message.put("message", "Error al buscar factura");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

    }

    /*
     * insertar factura , recibe un objeto factura y una lista de reservas, en cada
     * reserva debe modificarse el idbill con el id de la factura
     * devuelve un mensaje con el mensaje de exito o error y el objeto factura y las
     * reservas actualizadas
     */
    @PostMapping
    public ResponseEntity<?> saveBillAndBookings(@RequestBody BillAndBookingDTO billAndBookingsDTO) {
        // Se recibe un objeto Bill y una lista de Booking
        HashMap<String, Object> message = new HashMap<>();
        // Lista de IDs de reservas actualizadas
        List<Long> updatedBookingIds = new ArrayList<>();
        try {
            // Se recibe un objeto Bill y una lista de Booking
            Bill bill = billAndBookingsDTO.getBill();
            // Se obtiene la lista de reservas
            List<Booking> bookingUpdates = billAndBookingsDTO.getBookings();
            // Se guarda la factura
            Bill billSaved = service.save(bill);
            // Se recorren las reservas
            for (Booking bookingUpdate : bookingUpdates) {
                // Se busca la reserva por ID
                Optional<Booking> existingBookingOpt = bookingService.findById(bookingUpdate.getIdBooking());
                // Si se encuentra la reserva, se actualiza el idBill
                if (existingBookingOpt.isPresent()) {
                    // Si se encuentra la reserva, se actualiza el idBill
                    Booking existingBooking = existingBookingOpt.get();
                    // Establecer idBill
                    existingBooking.setBill(billSaved);
                    // Guardar reserva
                    bookingService.saveBooking(existingBooking);
                    // Agregar ID a la lista
                    updatedBookingIds.add(existingBooking.getIdBooking()); // Agregar ID a la lista
                } else {
                    // Si no se encuentra la reserva, devolver error
                    message.put("status", 404);
                    message.put("message", "No se encontró la reserva con ID: " + bookingUpdate.getIdBooking());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
                }
            }
            // Devolver mensaje de éxito con la factura y las reservas actualizadas
            message.put("status", 200);
            // message.put("message", "Las reservas" + bookingUpdates + "fueron
            // actualizadas");
            /*
             * agradecimiento a chat gpt porque me devolvia :
             * "Las
             * reservas[com.mtwproject.backend.mtwprojectbackend.models.entities.Booking@
             * 345b991b,
             * com.mtwproject.backend.mtwprojectbackend.models.entities.Booking@565128b2]
             * fueron actualizadas"
             * ES DECIR , DIRECCIONES DE MEMORIA, YA QUE la conversión de un objeto a un
             * string
             * utiliza el método toString() de la clase del objeto, que generalmente
             * devuelve la clase y su hash de memoria.
             */
            String bookingIdsStr = updatedBookingIds.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            message.put("message", "Las reservas con IDs " + bookingIdsStr + " fueron actualizadas");
            message.put("data", billSaved);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            // Devolver mensaje de error si ocurre una excepción
            message.put("status", 500);
            message.put("message", "Error al guardar factura: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    /*
     * Actualizar factura, recibe un objeto factura y/o no recibir una lista de
     * reservas
     */
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateBill(@RequestBody BillAndBookingDTO billAndBookingsDTO) {
        HashMap<String, Object> message = new HashMap<>();
        List<Long> updatedBookingIds = new ArrayList<>();
        try {
            // Se recibe un objeto Bill y una lista de Booking
            Bill bill = billAndBookingsDTO.getBill();
            // Se obtiene la lista de reservas
            List<Booking> bookingUpdates = billAndBookingsDTO.getBookings();
            // Se guarda la factura
            Bill billSaved = service.save(bill);
            // Se recorren las reservas
            if (bookingUpdates.isEmpty()) {
                // No se recibieron reservas, establecer idBill de las reservas actuales en null
                List<Booking> existingBookings = bookingService.findByBillId(bill.getIdBill());
                // Recorrer las reservas actuales y establecer idBill en null
                for (Booking booking : existingBookings) {
                    // Establecer idBill en null
                    booking.setBill(null);
                    // Guardar reserva
                    bookingService.saveBooking(booking);
                }
            } else {
                // Se recorren las reservas recibidas
                for (Booking bookingUpdate : bookingUpdates) {
                    // Se busca la reserva por ID
                    Optional<Booking> existingBookingOpt = bookingService.findById(bookingUpdate.getIdBooking());
                    if (existingBookingOpt.isPresent()) {
                        // Si se encuentra la reserva, se actualiza el idBill
                        Booking existingBooking = existingBookingOpt.get();
                        // Establecer idBill
                        existingBooking.setBill(billSaved);
                        // Guardar reserva
                        bookingService.saveBooking(existingBooking);
                        // Agregar ID a la lista
                        updatedBookingIds.add(existingBooking.getIdBooking()); // Agregar ID a la lista
                    } else {
                        // Si no se encuentra la reserva, devolver error
                        message.put("status", 404);
                        message.put("message", "No se encontró la reserva con ID: " + bookingUpdate.getIdBooking());
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
                    }
                }
            }
            // Devolver mensaje de éxito con la factura y las reservas actualizadas
            message.put("status", 200);
            String bookingIdsStr = updatedBookingIds.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            if (bookingIdsStr.isEmpty()) {
                message.put("message", "No se recibieron reservas, se eliminaron las reservas asociadas a la factura");
                message.put("data", billSaved);
            } else {
                message.put("message", "Las reservas con IDs " + bookingIdsStr + " fueron actualizadas");
                message.put("data", billSaved);

            }
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            // Devolver mensaje de error si ocurre una excepción
            message.put("status", 500);
            message.put("message", "Error al actualizar factura: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    /*
     * eliminar logico de factura, recibe el id de la factura
     * si hay reservas en esa factura, se pone idbill de las reservas en null
     * el estado de la factura se pone en inactivo
     */

    @PutMapping({ "/{id}" })
    @ResponseBody
    public ResponseEntity<?> deleteBill(@PathVariable("id") Long idBill) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            // Buscar factura por ID
            Optional<Bill> existingBillOpt = service.findById(idBill);
            if (existingBillOpt.isPresent()) {
                Bill existingBill = existingBillOpt.get();
                // Guardar factura
                Bill billSaved = service.save(existingBill);
                // Obtener reservas asociadas a la factura
                List<Booking> existingBookings = bookingService.findByBillId(idBill);
                // Recorrer las reservas actuales y establecer idBill en null
                for (Booking booking : existingBookings) {
                    // Establecer idBill en null
                    booking.setBill(null);
                    // Guardar reserva
                    bookingService.saveBooking(booking);
                }
                // Devolver mensaje de éxito con la factura y las reservas actualizadas
                message.put("status", 200);
                message.put("message", "Factura eliminada");
                message.put("data", billSaved);
                return ResponseEntity.ok(message);
            } else {
                // Si no se encuentra la factura, devolver error
                message.put("status", 404);
                message.put("message", "No se encontró la factura con ID: " + idBill);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        } catch (Exception e) {
            // Devolver mensaje de error si ocurre una excepción
            message.put("status", 500);
            message.put("message", "Error al eliminar factura: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

}
