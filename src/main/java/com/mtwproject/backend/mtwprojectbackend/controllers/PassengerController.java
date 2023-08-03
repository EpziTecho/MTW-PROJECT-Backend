package com.mtwproject.backend.mtwprojectbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Passenger;
import com.mtwproject.backend.mtwprojectbackend.services.PassengerService;



@RestController
public class PassengerController {

    @Autowired
    private PassengerService servicepassenger;

    
    @GetMapping("/passengers")
    public List<Passenger> list(){
        return servicepassenger.findAll();
    }
    
}

