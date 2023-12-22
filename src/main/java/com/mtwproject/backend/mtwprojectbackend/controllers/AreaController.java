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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Area;
import com.mtwproject.backend.mtwprojectbackend.services.AreaService;

@RestController
@RequestMapping("/areas") //ruta base : localhost:8080/areas
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping //sin ruta porque es la ruta base del tipo get
    public List <Area> list(){
        return areaService.findAll();
    }

    @GetMapping("/{id}") //ruta base : localhost:8080/areas/1
    public ResponseEntity<?> show(@PathVariable Long id){
       Optional<Area> areaOptional= areaService.findById(id);
       if(areaOptional.isPresent()){
              return ResponseEntity.ok(areaOptional.orElseThrow());
       }
       return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<?>create(@RequestBody Area area){
        return ResponseEntity.status(HttpStatus.CREATED).body(areaService.save(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Area area, @PathVariable Long id){
        Optional<Area> areaOptional= areaService.update(area, id);
        if(areaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(areaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional <Area> areaOptional= areaService.findById(id);
        if(areaOptional.isPresent()){
            areaService.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}
