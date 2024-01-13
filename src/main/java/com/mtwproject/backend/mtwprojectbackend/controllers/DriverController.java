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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Driver;
import com.mtwproject.backend.mtwprojectbackend.services.DriverService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    // Lista de conductores
    @GetMapping({ "", "/" })
    public ResponseEntity<?> driversList() {
        HashMap<String, Object> message = new HashMap<>();
        try {
            List<Driver> driversList = driverService.findAll();
            if (driversList.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No hay conductores registrados");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Lista de conductores");
            message.put("data", driversList);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al obtener la lista de conductores");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Buscar conductor por id
    @GetMapping("/findbyid/")
    @ResponseBody
    public ResponseEntity<?> findDriverById(@RequestBody Driver driver) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Driver> driverOptional = driverService.findById(driver.getIdDriver());
            if (driverOptional.isPresent()) {
                message.put("status", "200");
                message.put("message", "Conductor encontrado");
                message.put("data", driverOptional.get());
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Conductor no encontrado");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al obtener el conductor");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // insertar conductor
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveDriver(@RequestBody Driver driver) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Driver driverNew = driverService.save(driver);
            message.put("status", "200");
            message.put("message", "Conductor registrado");
            message.put("data", driverNew);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al registrar el conductor");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // actualizar conductor
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateDriver(@RequestBody Driver driver) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Driver> driverOptional = driverService.findById(driver.getIdDriver());
            if (driverOptional.isPresent()) {
                Driver driverUpdated = driverService.save(driver);
                message.put("status", "200");
                message.put("message", "Conductor actualizado");
                message.put("data", driverUpdated);
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Conductor no encontrado");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al actualizar el conductor");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Eliminar conductor
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<?> deleteDriver(@RequestBody Driver driver) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Driver> driverOptional = driverService.findById(driver.getIdDriver());
            if (driverOptional.isPresent()) {
                driverService.remove(driver.getIdDriver());
                message.put("status", "200");
                message.put("message", "Conductor eliminado");
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Conductor no encontrado");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al eliminar el conductor");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Eliminar conductor logicamente (cambiar estado)

    @PutMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> deactivateDriver(@RequestBody Driver driver) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            String result = driverService.deactivateDriver(driver.getIdDriver());
            if (result.equals("Conductor desactivado")) {
                message.put("status", "200");
                message.put("message", "Conductor desactivado");
                return ResponseEntity.ok(message);
            }
            message.put("status", "500");
            message.put("message", "Error al desactivar el conductor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al desactivar el conductor");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // @GetMapping
    // public List<Driver> list() {
    // return driverService.findAll();
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<?> show(@PathVariable Long id) {
    // Optional<Driver> driverOptional = driverService.findById(id);
    // if (driverOptional.isPresent()) {
    // return ResponseEntity.ok(driverOptional.orElseThrow());
    // }
    // return ResponseEntity.notFound().build();
    // }

    // @PostMapping
    // public ResponseEntity<?> create(@RequestBody Driver driver) {
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(driverService.save(driver));
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<?> update(@RequestBody Driver driver, @PathVariable
    // Long id) {
    // Optional<Driver> driverOptional = driverService.update(driver, id);
    // if (driverOptional.isPresent()) {
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(driverOptional.orElseThrow());
    // }
    // return ResponseEntity.notFound().build();
    // }

    // // Desactivar conductor
    // @PutMapping("/delete")
    // public ResponseEntity<?> deactivateDriver(@RequestBody Driver driver) {
    // String result = driverService.deactivateDriver(driver.getIdDriver());
    // HashMap<String, Object> response = new HashMap<>();
    // response.put("message", result);
    // if (result.equals("Conductor desactivado")) {
    // return ResponseEntity.ok(response);
    // } else {
    // response.put("message", "No se pudo desactivar el conductor");
    // response.put("error", true);
    // return
    // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    // }
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> remove(@PathVariable Long id) {
    // Optional<Driver> driverOptional = driverService.findById(id);
    // if (driverOptional.isPresent()) {
    // driverService.remove(id);
    // return ResponseEntity.ok().build();
    // }
    // return ResponseEntity.notFound().build();
    // }

}
