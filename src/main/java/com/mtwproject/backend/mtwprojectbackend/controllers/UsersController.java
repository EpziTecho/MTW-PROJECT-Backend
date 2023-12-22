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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Users;

import com.mtwproject.backend.mtwprojectbackend.services.UsersService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService serviceuser;

    
    @GetMapping
    public List<Users> list(){
        return serviceuser.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable  Long id){
        Optional <Users> userOptional= serviceuser.findById(id);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Users user){
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceuser.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Users user, @PathVariable Long id){
        Optional <Users> userOptional= serviceuser.update(user, id);
        if(userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional <Users> userOptional= serviceuser.findById(id);
        if(userOptional.isPresent()){
            serviceuser.remove(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}

