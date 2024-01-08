package com.mtwproject.backend.mtwprojectbackend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
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

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Ubigeo;
import com.mtwproject.backend.mtwprojectbackend.services.UbigeoService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ubigeo")
public class UbigeoController {

    @Autowired
    private UbigeoService ubigeoService;

    private List<Ubigeo> ubigeoList;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<Ubigeo>> ubigeoList() {
        HashMap<String, Object> message = new HashMap<>();
        try {
            ubigeoList = ubigeoService.listUbigeosLimaCallao();
            if (ubigeoList.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No se encontraron distritos");
                return ResponseEntity.ok(ubigeoList);
            }
            message.put("status", "200");
            message.put("message", "Se encontraron distritos");
            message.put("ubigeo", ubigeoList);
            return ResponseEntity.ok(ubigeoList);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al buscar los distritos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ubigeoList);
        }
    }

    @GetMapping("/department/{department}")
    @ResponseBody
    public ResponseEntity<?> findUbigeoByDepartment(@PathVariable("department") String department) {
        ubigeoList = ubigeoService.listUbigeosLimaCallao();
        List<Ubigeo> ubigeo = ubigeoList.stream()
                .filter(u -> u.getDepartment().equals(department))
                .collect(Collectors.toList());
        HashMap<String, Object> message = new HashMap<>();
        try {
            if (ubigeo.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No se encontraron distritos en el departamento especificado");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Se encontraron distritos en el departamento especificado");
            message.put("ubigeo", ubigeo);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al buscar los distritos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @GetMapping("/department/{department}/province/{province}")
    @ResponseBody
    public ResponseEntity<?> findUbigeoByDepartmentAndProvince(@PathVariable("department") String department,
            @PathVariable("province") String province) {
        ubigeoList = ubigeoService.listUbigeosLimaCallao();
        List<Ubigeo> ubigeo = ubigeoList.stream()
                .filter(u -> u.getDepartment().equals(department) && u.getProvince().equals(province))
                .collect(Collectors.toList());
        HashMap<String, Object> message = new HashMap<>();
        try {
            if (ubigeo.isEmpty()) {
                message.put("status", "404");
                message.put("message", "No se encontraron distritos en el departamento y provincia especificados");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Se encontraron distritos en el departamento y provincia especificados");
            message.put("ubigeo", ubigeo);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al buscar los distritos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @GetMapping("/department/{department}/province/{province}/district/{district}")
    @ResponseBody
    public ResponseEntity<?> findUbigeoByDepartmentAndProvinceAndDistrict(@PathVariable("department") String department,
            @PathVariable("province") String province, @PathVariable("district") String district) {
        ubigeoList = ubigeoService.listUbigeosLimaCallao();
        List<Ubigeo> ubigeo = ubigeoList.stream()
                .filter(u -> u.getDepartment().equals(department) && u.getProvince().equals(province)
                        && u.getDistrict().equals(district))
                .collect(Collectors.toList());
        HashMap<String, Object> message = new HashMap<>();
        try {
            if (ubigeo.isEmpty()) {
                message.put("status", "404");
                message.put("message",
                        "No se encontraron distritos en el departamento, provincia y distrito especificados");
                return ResponseEntity.ok(message);
            }
            message.put("status", "200");
            message.put("message", "Se encontraron distritos en el departamento, provincia y distrito especificados");
            message.put("ubigeo", ubigeo);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("status", "500");
            message.put("message", "Se produjo un error al buscar los distritos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<?> show(@PathVariable Long id) {

    // Optional<Ubigeo> UbigeoOptional = ubigeoService.findById(id);
    // if (UbigeoOptional.isPresent()) {
    // return ResponseEntity.ok(UbigeoOptional.orElseThrow());
    // }
    // return ResponseEntity.notFound().build();
    // }

    // @PostMapping
    // public ResponseEntity<?> create(@RequestBody Ubigeo ubigeo) {
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(ubigeoService.save(ubigeo));
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<?> update(@RequestBody Ubigeo ubigeo, @PathVariable
    // Long id) {
    // Optional<Ubigeo> ubigeoOptional = ubigeoService.update(ubigeo, id);
    // if (ubigeoOptional.isPresent()) {
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(ubigeoOptional.orElseThrow());
    // }
    // return ResponseEntity.notFound().build();
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> remove(@PathVariable Long id) {
    // Optional<Ubigeo> ubigeoOptional = ubigeoService.findById(id);
    // if (ubigeoOptional.isPresent()) {
    // ubigeoService.remove(id);
    // return ResponseEntity.noContent().build();
    // }
    // return ResponseEntity.notFound().build();
    // }

    // @GetMapping("/department/{department}")
    // public List<Ubigeo> findByDepartment(@PathVariable String department) {
    // return ubigeoService.findByDepartment(department);
    // }

    // // Provincia dentro del departamento especificado
    // @GetMapping("/department/{department}/province/{province}")
    // public List<Ubigeo> findByDepartmentAndProvince(
    // @PathVariable String department,
    // @PathVariable String province) {
    // return ubigeoService.findByDepartmentAndProvince(department, province);
    // }

    // // Distrito dentro de un departamento y provincia específicos
    // @GetMapping("/department/{department}/province/{province}/district/{district}")
    // public List<Ubigeo> findByDepartmentAndProvinceAndDistrict(
    // @PathVariable String department,
    // @PathVariable String province,
    // @PathVariable String district) {
    // return ubigeoService.findByDepartmentAndProvinceAndDistrict(department,
    // province, district);
    // }
}
