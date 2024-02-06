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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Currency;
import com.mtwproject.backend.mtwprojectbackend.services.CurrencyService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    // Lista de monedas

    @GetMapping({ "", "/" })
    public ResponseEntity<?> currencyList() {
        HashMap<String, Object> message = new HashMap<>();
        try {
            List<Currency> currencyList = currencyService.findAll();
            if (currencyList.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No hay monedas registradas");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Lista de monedas");
            message.put("data", currencyList);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al obtener la lista de monedas");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

    }

    // Buscar moneda por id
    @GetMapping("/findbyid/")
    @ResponseBody
    public ResponseEntity<?> findCurrencyById(@RequestBody Currency currency) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Currency> currencyOptional = currencyService.findById(currency.getIdCurrency());
            if (currencyOptional.isPresent()) {
                message.put("status", "200");
                message.put("message", "Moneda encontrada");
                message.put("data", currencyOptional.orElseThrow());
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Moneda no encontrada");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al obtener la moneda");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // insertar moneda
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveCurrency(@RequestBody Currency currency) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Currency currencyNew = currencyService.save(currency);
            message.put("status", "201");
            message.put("message", "Moneda creada");
            message.put("data", currencyNew);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al crear la moneda");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // actualizar moneda
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateCurrency(@RequestBody Currency currency) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Currency> currencyOptional = currencyService.findById(currency.getIdCurrency());
            if (currencyOptional.isPresent()) {
                Currency currencyNew = currencyService.save(currency);
                message.put("status", "200");
                message.put("message", "Moneda actualizada");
                message.put("data", currencyNew);
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Moneda no encontrada");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al actualizar la moneda");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // eliminar moneda
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<?> deleteCurrency(@RequestBody Currency currency) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Currency> currencyOptional = currencyService.findById(currency.getIdCurrency());
            if (currencyOptional.isPresent()) {
                currencyService.remove(currency.getIdCurrency());
                message.put("status", "200");
                message.put("message", "Moneda eliminada");
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Moneda no encontrada");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al eliminar la moneda");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // @GetMapping
    // public List <Currency > list(){
    // return currencyService.findAll();
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<?> show(@PathVariable Long id){
    // Optional <Currency> currencyOptional= currencyService.findById(id);
    // if(currencyOptional.isPresent()){
    // return ResponseEntity.ok(currencyOptional.orElseThrow());
    // }
    // return ResponseEntity.notFound().build();
    // }

    // @PostMapping
    // public ResponseEntity<?> create(@RequestBody Currency currency){
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(currencyService.save(currency));
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<?> update(@RequestBody Currency currency, @PathVariable
    // Long id){
    // Optional <Currency> currencyOptional= currencyService.update(currency, id);
    // if(currencyOptional.isPresent()){
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(currencyOptional.orElseThrow());
    // }
    // return ResponseEntity.notFound().build();
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> remove(@PathVariable Long id){
    // Optional <Currency> currencyOptional= currencyService.findById(id);
    // if(currencyOptional.isPresent()){
    // currencyService.remove(id);
    // return ResponseEntity.ok().build();
    // }
    // return ResponseEntity.notFound().build();
    // }
}
