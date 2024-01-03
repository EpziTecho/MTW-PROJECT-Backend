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
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Driver;
import com.mtwproject.backend.mtwprojectbackend.services.DriverService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    // Desactivar conductor
    @PutMapping("/delete")
    public ResponseEntity<?> deactivateDriver(@RequestBody Driver driver){
       String result = driverService.deactivateDriver(driver.getIdDriver());
       HashMap<String, Object> response = new HashMap<>(); 
       response.put("message", result); 
     if (result.equals("Conductor desactivado")) {
         return ResponseEntity.ok(response);
     } else {
        response.put("message", "No se pudo desactivar el conductor");
        response.put("error", true);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);        
     }  
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
