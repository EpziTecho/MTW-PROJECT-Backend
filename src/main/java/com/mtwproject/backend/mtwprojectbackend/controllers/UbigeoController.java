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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Ubigeo;
import com.mtwproject.backend.mtwprojectbackend.services.UbigeoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ubigeo")
public class UbigeoController {

    @Autowired
    private UbigeoService ubigeoService;

    @GetMapping
    public List<Ubigeo> list() {
        return ubigeoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Optional<Ubigeo> UbigeoOptional = ubigeoService.findById(id);
        if (UbigeoOptional.isPresent()) {
            return ResponseEntity.ok(UbigeoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Ubigeo ubigeo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ubigeoService.save(ubigeo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Ubigeo ubigeo, @PathVariable Long id) {
        Optional<Ubigeo> ubigeoOptional = ubigeoService.update(ubigeo, id);
        if (ubigeoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ubigeoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Ubigeo> ubigeoOptional = ubigeoService.findById(id);
        if (ubigeoOptional.isPresent()) {
            ubigeoService.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/department/{department}")
    public List<Ubigeo> findByDepartment(@PathVariable String department) {
        return ubigeoService.findByDepartment(department);
    }

    // Provincia dentro del departamento especificado
    @GetMapping("/department/{department}/province/{province}")
    public List<Ubigeo> findByDepartmentAndProvince(
            @PathVariable String department,
            @PathVariable String province) {
        return ubigeoService.findByDepartmentAndProvince(department, province);
    }

    // Distrito dentro de un departamento y provincia específicos
    @GetMapping("/department/{department}/province/{province}/district/{district}")
    public List<Ubigeo> findByDepartmentAndProvinceAndDistrict(
            @PathVariable String department,
            @PathVariable String province,
            @PathVariable String district) {
        return ubigeoService.findByDepartmentAndProvinceAndDistrict(department, province, district);
    }
}
