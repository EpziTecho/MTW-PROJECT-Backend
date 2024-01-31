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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Accounting;
import com.mtwproject.backend.mtwprojectbackend.services.AccountingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/accounting")
public class AccountingController {

    @Autowired
    private AccountingService accountingService;

    // Listar los registros de la tabla Accounting
    @GetMapping({ "", "/" })
    public ResponseEntity<?> accountingList() {
        HashMap<String, Object> message = new HashMap<>();
        try {
            List<Accounting> accountingList = accountingService.findAll();
            if (accountingList.isEmpty()) {
                message.put("status", "200");
                message.put("message", "No se encontraron registros en la tabla Accounting");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Lista de registros de la tabla Accounting");
            message.put("data", accountingList);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("message", "Error al intentar listar los registros de la tabla Accounting");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

    }

    // buscar registro por id
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> findAccountingById(@PathVariable("id") Long idAccounting) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Accounting> accountingOptional = accountingService.findById(idAccounting);
            if (accountingOptional.isPresent()) {
                message.put("status", "200");
                message.put("message", "Registro encontrado");
                message.put("data", accountingOptional.orElseThrow());
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Registro no encontrado");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("message", "Error al intentar buscar el registro");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Crear un nuevo registro
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveAccounting(@RequestBody Accounting accounting) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Accounting accountingCreated = accountingService.save(accounting);
            message.put("status", "201");
            message.put("message", "Registro N ° " + accountingCreated.getIdAccounting() + " creado con éxito");
            message.put("data", accountingCreated);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al intentar crear el registro");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Actualizar un registro
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateAccounting(@RequestBody Accounting accounting) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Accounting> accountingOptional = accountingService.update(accounting,
                    accounting.getIdAccounting());
            if (accountingOptional.isPresent()) {
                message.put("status", "200");
                message.put("message",
                        "Registro N ° " + accountingOptional.get().getIdAccounting() + " actualizado con éxito");
                message.put("data", accountingOptional.orElseThrow());
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Registro no encontrado");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al intentar actualizar el registro N ° " + accounting.getIdAccounting());
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // //Cambiar estado del registro a pagado

    @PutMapping("/pay")
    @ResponseBody
    public ResponseEntity<?> updateStatusAccounting(@RequestBody Accounting accounting) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Accounting> accountingOptional = accountingService.updateStatus(accounting,
                    accounting.getIdAccounting());
            if (accountingOptional.isPresent()) {
                message.put("status", "200");
                message.put("message",
                        "Registro N ° " + accountingOptional.get().getIdAccounting() + " actualizado con éxito");
                message.put("data", accountingOptional.orElseThrow());
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Registro no encontrado");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al intentar actualizar el registro N ° " + accounting.getIdAccounting());
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<?> deleteAccounting(@RequestBody Accounting accounting) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            accountingService.remove(accounting.getIdAccounting());
            message.put("status", "200");
            message.put("message", "Registro N ° " + accounting.getIdAccounting() + " eliminado con éxito");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al intentar eliminar el registro N ° " + accounting.getIdAccounting());
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

}
