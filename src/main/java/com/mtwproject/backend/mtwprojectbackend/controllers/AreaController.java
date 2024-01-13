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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Area;
import com.mtwproject.backend.mtwprojectbackend.services.AreaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/areas") // ruta base : localhost:8080/areas
public class AreaController {

    @Autowired
    private AreaService areaService;

    // Lista de areas
    @GetMapping
    public ResponseEntity<?> areList() {
        HashMap<String, Object> message = new HashMap<>();
        try {
            List<Area> areaList = areaService.findAll();
            if (areaList.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No hay areas registradas");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Lista de areas");
            message.put("data", areaList);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al obtener la lista de areas");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Buscar area por id
    @GetMapping("/findbyid/")
    @ResponseBody
    public ResponseEntity<?> findAreaById(@RequestBody Area area) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Area> areaFound = areaService.findById(area.getIdArea());
            if (areaFound.isPresent()) {
                message.put("status", "200");
                message.put("message", "Area encontrada");
                message.put("data", areaFound);
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Area no encontrada");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al obtener el area");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Insertar area
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Area area) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Area areaCreated = areaService.save(area);
            message.put("status", "201");
            message.put("message", "Area creada");
            message.put("data", areaCreated);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al crear el area");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Actualizar area
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateArea(@RequestBody Area area) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Area> areaOptional = areaService.update(area, area.getIdArea());
            if (areaOptional.isPresent()) {
                Area areaUpdated = areaService.save(area);
                message.put("status", "200");
                message.put("message", "Area actualizada");
                message.put("data", areaUpdated);
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Area no encontrada");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al actualizar el area");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // Eliminar area
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<?> removeArea(@RequestBody Area area) {
        HashMap<String, Object> message = new HashMap<>();
        try {
            Optional<Area> areaOptional = areaService.findById(area.getIdArea());
            if (areaOptional.isPresent()) {
                areaService.remove(area.getIdArea());
                message.put("status", "200");
                message.put("message", "Area eliminada");
                return ResponseEntity.ok(message);
            }
            message.put("status", "404");
            message.put("message", "Area no encontrada");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Error al eliminar el area");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
}
// @GetMapping
// public List <Area> list(){
// return areaService.findAll();
// }

// @GetMapping("/{id}") //ruta base : localhost:8080/areas/1
// public ResponseEntity<?> show(@PathVariable Long id){
// Optional<Area> areaOptional= areaService.findById(id);
// if(areaOptional.isPresent()){
// return ResponseEntity.ok(areaOptional.orElseThrow());
// }
// return ResponseEntity.notFound().build();
// }

// @PostMapping
// public ResponseEntity<?>create(@RequestBody Area area){
// return
// ResponseEntity.status(HttpStatus.CREATED).body(areaService.save(area));
// }

// @PutMapping("/{id}")
// public ResponseEntity<?> update(@RequestBody Area area, @PathVariable Long
// id){
// Optional<Area> areaOptional= areaService.update(area, id);
// if(areaOptional.isPresent()){
// return
// ResponseEntity.status(HttpStatus.CREATED).body(areaOptional.orElseThrow());
// }
// return ResponseEntity.notFound().build();
// }
// @DeleteMapping("/{id}")
// public ResponseEntity<?> remove(@PathVariable Long id){
// Optional <Area> areaOptional= areaService.findById(id);
// if(areaOptional.isPresent()){
// areaService.remove(id);
// return ResponseEntity.noContent().build();
// }
// return ResponseEntity.notFound().build();

// }
