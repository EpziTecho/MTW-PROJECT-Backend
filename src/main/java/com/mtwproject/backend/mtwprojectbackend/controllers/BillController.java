package com.mtwproject.backend.mtwprojectbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Bill;
import com.mtwproject.backend.mtwprojectbackend.services.BillService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BillController {

    @Autowired
    private BillService service;

    @GetMapping("/bills")
    public List<Bill> list(){
        return service.findAll();
    }
    

}
