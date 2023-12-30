package com.mtwproject.backend.mtwprojectbackend.controllers;

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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Currency;
import com.mtwproject.backend.mtwprojectbackend.services.CurrencyService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List <Currency > list(){
        return currencyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional <Currency> currencyOptional= currencyService.findById(id);
        if(currencyOptional.isPresent()){
            return ResponseEntity.ok(currencyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Currency currency){
        return ResponseEntity.status(HttpStatus.CREATED).body(currencyService.save(currency));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Currency currency, @PathVariable Long id){
        Optional <Currency> currencyOptional= currencyService.update(currency, id);
        if(currencyOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(currencyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional <Currency> currencyOptional= currencyService.findById(id);
        if(currencyOptional.isPresent()){
            currencyService.remove(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
