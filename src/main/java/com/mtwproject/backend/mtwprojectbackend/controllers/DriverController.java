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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Driver;
import com.mtwproject.backend.mtwprojectbackend.services.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {
    
    @Autowired
    private DriverService driverService;


    @GetMapping
    public List <Driver> list(){
        return driverService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional <Driver> driverOptional= driverService.findById(id);
        if(driverOptional.isPresent()){
            return ResponseEntity.ok(driverOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Driver driver){
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.save(driver));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Driver driver, @PathVariable Long id){
        Optional <Driver> driverOptional= driverService.update(driver, id);
        if(driverOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(driverOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional <Driver> driverOptional= driverService.findById(id);
        if(driverOptional.isPresent()){
            driverService.remove(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    
}
