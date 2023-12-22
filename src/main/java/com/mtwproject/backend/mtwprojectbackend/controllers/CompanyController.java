package com.mtwproject.backend.mtwprojectbackend.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Company;
import com.mtwproject.backend.mtwprojectbackend.services.CompanyService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/company")
public class CompanyController {
  @Autowired  
  private CompanyService companyService;

    @GetMapping
    public List <Company > list(){
        return companyService.findAll();
    }  

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
     Optional <Company> companyOptional= companyService.findById(id);
        if(companyOptional.isPresent()){
        return ResponseEntity.ok(companyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
     }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Company company, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        String errorMessages = bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

    if (companyService.findByBusinessName(company.getBusinessName()).isPresent() || 
        companyService.findByIdNumber(company.getIdNumber()).isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("ya existe- Holi desde /company.");
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Company company, @PathVariable Long id){
        Optional <Company> companyOptional= companyService.update(company, id);
        if(companyOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(companyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional <Company> companyOptional= companyService.findById(id);
        if(companyOptional.isPresent()){
            companyService.remove(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
public ResponseEntity<List<Company>> search(@RequestParam("q") String query) {
    List<Company> companies = companyService.findByCriteria(query);
    // if (companies.isEmpty()) {
    //     return ResponseEntity.notFound().build();
    // }
    return ResponseEntity.ok(companies);
}
}
    

