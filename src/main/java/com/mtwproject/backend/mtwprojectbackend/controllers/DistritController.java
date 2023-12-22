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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Distrit;
import com.mtwproject.backend.mtwprojectbackend.services.DistritService;

@RestController
@RequestMapping("/distrit")
public class DistritController {
    
    @Autowired
    private DistritService distritService;


    @GetMapping
    public List <Distrit > list(){
        return distritService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show (@PathVariable Long id){

        Optional <Distrit> distritOptional= distritService.findById(id);
        if(distritOptional.isPresent()){
            return ResponseEntity.ok(distritOptional.orElseThrow());
        }   
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Distrit distrit){
        return ResponseEntity.status(HttpStatus.CREATED).body(distritService.save(distrit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Distrit distrit, @PathVariable Long id){
        Optional <Distrit> distritOptional= distritService.update(distrit, id);
        if(distritOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(distritOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional <Distrit> distritOptional= distritService.findById(id);
        if(distritOptional.isPresent()){
            distritService.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

