package com.mtwproject.backend.mtwprojectbackend.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Accounting;
import com.mtwproject.backend.mtwprojectbackend.services.AccountingService;

@RestController
@RequestMapping("/accounting")
public class AccountingController {
    
    @Autowired
    private AccountingService accountingService;

    @GetMapping
    public List <Accounting > list(){
        return accountingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable  Long id){
        Optional <Accounting> accountingOptional= accountingService.findById(id);
        if(accountingOptional.isPresent()){
            return ResponseEntity.ok(accountingOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
        
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Accounting accounting){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountingService.save(accounting));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Accounting accounting, @PathVariable Long id){
        Optional <Accounting> accountingOptional= accountingService.update(accounting, id);
        if(accountingOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(accountingOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional <Accounting> accountingOptional= accountingService.findById(id);
        if(accountingOptional.isPresent()){
            accountingService.remove(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
