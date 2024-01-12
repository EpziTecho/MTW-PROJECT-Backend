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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Passenger;
import com.mtwproject.backend.mtwprojectbackend.services.PassengerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService servicepassenger;

    // Lista de pasajeros
    @GetMapping({ "", "/" })
    public ResponseEntity<?> passengersList() {
        HashMap<String, Object> message = new HashMap<>();
        try {
            List<Passenger> passengersList = servicepassenger.findAll();
            if (passengersList.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No hay pasajeros registrados");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Lista de pasajeros");
            message.put("data", passengersList);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al obtener la lista de pasajeros");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Buscar pasajero por id
    @GetMapping("/findbyid/")
    @ResponseBody
    public ResponseEntity<?> findPassengerById(@RequestBody Passenger passenger) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Passenger> passengerOptional = servicepassenger.findById(passenger.getIdPassenger());
            if (passengerOptional.isPresent()) {
                message.put("status", "200");
                message.put("message", "Pasajero encontrado");
                message.put("data", passengerOptional.orElseThrow());
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Pasajero no encontrado");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al buscar el pasajero");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> savePassenger(@RequestBody Passenger passenger) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Passenger passengerSaved = servicepassenger.save(passenger);
            message.put("status", "200");
            message.put("message", "Pasajero creado");
            message.put("data", passengerSaved);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al crear el pasajero");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

    }

    // Actualizar pasajero
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updatePassenger(@RequestBody Passenger passenger) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Passenger> passengerOptional = servicepassenger.update(passenger, passenger.getIdPassenger());
            if (passengerOptional.isPresent()) {
                message.put("status", "200");
                message.put("message", "Pasajero actualizado");
                message.put("data", passengerOptional.orElseThrow());
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Pasajero no encontrado");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al actualizar el pasajero");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Eliminar pasajero
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<?> deletePassenger(@RequestBody Passenger passenger) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Passenger> passengerOptional = servicepassenger.findById(passenger.getIdPassenger());
            if (passengerOptional.isPresent()) {
                servicepassenger.remove(passenger.getIdPassenger());
                message.put("status", "200");
                message.put("message", "Pasajero eliminado");
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Pasajero no encontrado");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al eliminar el pasajero");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Eliminar pasajero logico / desactivar pasajero
    @PutMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> deactivatePassenger(@RequestBody Passenger passenger) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            String result = servicepassenger.deactivatePassenger(passenger.getIdPassenger());
            if (result.equals("Pasajero desactivado")) {
                message.put("status", "200");
                message.put("message", result);
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Pasajero no encontrado");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al desactivar el pasajero");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

}

///////////////////////////////////////////////////////////////////////////////////////////
// @GetMapping
// public List<Passenger> list() {
// return servicepassenger.findAll();
// }

// @GetMapping("/{id}")
// public ResponseEntity<?> show(@PathVariable Long id) {
// Optional<Passenger> passengerOptional = servicepassenger.findById(id);
// if (passengerOptional.isPresent()) {
// return ResponseEntity.ok(passengerOptional.orElseThrow());
// }
// return ResponseEntity.notFound().build();
// }

// @PostMapping
// public ResponseEntity<?> create(@RequestBody Passenger passenger) {
// return
// ResponseEntity.status(HttpStatus.CREATED).body(servicepassenger.save(passenger));
// }

// @PutMapping("/{id}")
// public ResponseEntity<?> update(@RequestBody Passenger passenger,
// @PathVariable Long id) {
// Optional<Passenger> passengerOptional = servicepassenger.update(passenger,
// id);
// if (passengerOptional.isPresent()) {
// return
// ResponseEntity.status(HttpStatus.CREATED).body(passengerOptional.orElseThrow());
// }
// return ResponseEntity.notFound().build();
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<?> remove(@PathVariable Long id) {
// Optional<Passenger> passengerOptional = servicepassenger.findById(id);
// if (passengerOptional.isPresent()) {
// servicepassenger.remove(id);
// return ResponseEntity.ok().build();
// }
// return ResponseEntity.notFound().build();
// }

// @PutMapping("/delete")
// public ResponseEntity<?> deactivatePassenger(@RequestBody Passenger
// passenger) {
// String result =
// servicepassenger.deactivatePassenger(passenger.getIdPassenger());
// HashMap<String, Object> response = new HashMap<>();
// response.put("message", result);
// if (result.equals("Pasajero desactivado")) {
// return ResponseEntity.ok(response);
// } else {
// response.put("message", "No se pudo desactivar el conductor");
// response.put("error", true);
// return
// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
// }

// }
// }
