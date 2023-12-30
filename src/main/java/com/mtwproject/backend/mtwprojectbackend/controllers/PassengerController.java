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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Passenger;
import com.mtwproject.backend.mtwprojectbackend.services.PassengerService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService servicepassenger;

    
    @GetMapping
    public List<Passenger> list(){
        return servicepassenger.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable  Long id){
        Optional <Passenger> passengerOptional= servicepassenger.findById(id);
        if(passengerOptional.isPresent()){
            return ResponseEntity.ok(passengerOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Passenger passenger){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicepassenger.save(passenger));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Passenger passenger, @PathVariable Long id){
        Optional <Passenger> passengerOptional= servicepassenger.update(passenger, id);
        if(passengerOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(passengerOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional <Passenger> passengerOptional= servicepassenger.findById(id);
        if(passengerOptional.isPresent()){
            servicepassenger.remove(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}

