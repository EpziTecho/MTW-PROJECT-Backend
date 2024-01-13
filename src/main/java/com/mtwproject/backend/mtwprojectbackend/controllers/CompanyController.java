package com.mtwproject.backend.mtwprojectbackend.controllers;

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
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

//Lista de empresas
@GetMapping({ "", "/" })
 public ResponseEntity <?> companyList(){
    HashMap <String, Object> message = new HashMap<>();
    try{
        List <Company> companies = companyService.findAll();
        if(companies.isEmpty()){
            message.put("status", "404");
            message.put("message", "No hay empresas registradas");
            return ResponseEntity.ok(message);
        }
        message.put("status", "200");
        message.put("message", "Lista de empresas");
        message.put("data", companies);
        return ResponseEntity.ok(message);
    }catch(Exception e){
        message.put("status", "500");
        message.put("message", "Se produjo un error al listar las empresas");
        message.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);

    }
   
 }

//Buscar empresa por id
@GetMapping("/findbyid/")
@ResponseBody
public ResponseEntity<?> findCompanyById(@RequestBody Company company){
    HashMap <String, Object> message = new HashMap<>();
    try{
        Optional <Company> companyOptional = companyService.findById(company.getIdCompany());
        if(companyOptional.isPresent()){
            message.put("status", "200");
            message.put("message", "Empresa encontrada");
            message.put("data", companyOptional.get());
            return ResponseEntity.ok(message);
        }
        message.put("status", "404");
        message.put("message", "Empresa no encontrada");
        return ResponseEntity.ok(message);
    }catch(Exception e){
        message.put("status", "500");
        message.put("message", "Se produjo un error al buscar la empresa");
        message.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}


//insertar empresa
@PostMapping
@ResponseBody
public ResponseEntity<?> saveCompany(@Valid @RequestBody Company company, BindingResult bindingResult) {
    HashMap<String, Object> message = new HashMap<>();

    if (bindingResult.hasErrors()) {
        String errorMessages = bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        message.put("status", "400");
        message.put("message", "Error de validación en los datos de la empresa");
        message.put("errors", errorMessages);
        return ResponseEntity.badRequest().body(message);
    }

    // Utiliza la función de comprobación para verificar si la empresa ya existe
    Optional<String> companyExistsError = checkCompanyExists(company.getBusinessName(), company.getIdNumber());
    if (companyExistsError.isPresent()) {
        message.put("status", "409");
        message.put("message", companyExistsError.get());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    try {
        companyService.save(company);
        message.put("status", "200");
        message.put("message", "Empresa registrada con éxito");
        message.put("data", company);
        return ResponseEntity.ok(message);
    } catch (Exception e) {
        message.put("status", "500");
        message.put("message", "Se produjo un error al registrar la empresa");
        message.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}

//actualizar empresa
@PutMapping
@ResponseBody
public ResponseEntity<?> updateCompany(@Valid @RequestBody Company company, BindingResult bindingResult){
    HashMap<String, Object> message = new HashMap<>();

    // Validación de los datos de entrada
    if (bindingResult.hasErrors()) {
        String errorMessages = bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        message.put("status", "400");
        message.put("message", "Error de validación en los datos de la empresa");
        message.put("errors", errorMessages);
        return ResponseEntity.badRequest().body(message);
    }

    try {
        Optional<Company> companyOptional = companyService.findById(company.getIdCompany());
        if(companyOptional.isPresent()){
            Company existingCompany = companyOptional.get();
            // Comprobar si los datos clave han cambiado y si hay conflicto con otra empresa
            if (!existingCompany.getBusinessName().equals(company.getBusinessName()) || 
                !existingCompany.getIdNumber().equals(company.getIdNumber())) {

                Optional<String> companyExistsError = checkCompanyExists(company.getBusinessName(), company.getIdNumber());
                if (companyExistsError.isPresent()) {
                    message.put("status", "409");
                    message.put("message", companyExistsError.get());
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
                }
            }

            companyService.save(company);
            message.put("status", "200");
            message.put("message", "Empresa actualizada con éxito");
            message.put("data", company);
            return ResponseEntity.ok(message);
        }
        message.put("status", "404");
        message.put("message", "Empresa no encontrada");
        return ResponseEntity.ok(message);
    }catch(Exception e){
        message.put("status", "500");
        message.put("message", "Se produjo un error al actualizar la empresa");
        message.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}

//eliminar empresa
@DeleteMapping
@ResponseBody
public ResponseEntity<?> deleteCompany(@RequestBody Company company){
    HashMap <String, Object> message = new HashMap<>();
    try{
        Optional <Company> companyOptional = companyService.findById(company.getIdCompany());
        if(companyOptional.isPresent()){
            companyService.remove(company.getIdCompany());
            message.put("status", "200");
            message.put("message", "Empresa eliminada");
            return ResponseEntity.ok(message);
        }
        message.put("status", "404");
        message.put("message", "Empresa no encontrada");
        return ResponseEntity.ok(message);
    }catch(Exception e){
        message.put("status", "500");
        message.put("message", "Se produjo un error al eliminar la empresa");
        message.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}

private Optional<String> checkCompanyExists(String businessName, String idNumber) {
    if (companyService.findByBusinessName(businessName).isPresent()) {
        return Optional.of("Ya existe una empresa con el mismo nombre comercial");
    }
    if (companyService.findByIdNumber(idNumber).isPresent()) {
        return Optional.of("Ya existe una empresa con el mismo número de identificación");
    }
    return Optional.empty();
}

// buscar company por criterios 
//http://localhost:8080/company/search?q="criterio" , puede ser el nombre de la empresa, el número de identificación o el teléfono  
@GetMapping("/search")
@ResponseBody
public ResponseEntity<?> search(@RequestParam("q") String query) {
   HashMap <String, Object> message = new HashMap<>();
    try{
        List<Company> companies = companyService.findByCriteria(query);
        if(companies.isEmpty()){
            message.put("status", "404");
            message.put("message", "No hay empresas registradas");
            return ResponseEntity.ok(message);
        }
        message.put("status", "200");
        message.put("message", "Lista de empresas");
        message.put("data", companies);
        return ResponseEntity.ok(message);
    }catch(Exception e){
        message.put("status", "500");
        message.put("message", "Se produjo un error al listar las empresas");
        message.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);

    }
}

//     @GetMapping
//     public List <Company > list(){
//         return companyService.findAll();
//     }  

//     @GetMapping("/{id}")
//     public ResponseEntity<?> show(@PathVariable Long id){
//      Optional <Company> companyOptional= companyService.findById(id);
//         if(companyOptional.isPresent()){
//         return ResponseEntity.ok(companyOptional.orElseThrow());
//         }
//         return ResponseEntity.notFound().build();
//      }

//     @PostMapping
//     public ResponseEntity<?> create(@Valid @RequestBody Company company, BindingResult bindingResult) {
//     if (bindingResult.hasErrors()) {
//         String errorMessages = bindingResult.getAllErrors().stream()
//                 .map(ObjectError::getDefaultMessage)
//                 .collect(Collectors.joining("; "));
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
//     }

//     if (companyService.findByBusinessName(company.getBusinessName()).isPresent() || 
//         companyService.findByIdNumber(company.getIdNumber()).isPresent()) {
//         return ResponseEntity.status(HttpStatus.CONFLICT).body("ya existe- Holi desde /company.");
//     }
//     return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<?> update(@RequestBody Company company, @PathVariable Long id){
//         Optional <Company> companyOptional= companyService.update(company, id);
//         if(companyOptional.isPresent()){
//             return ResponseEntity.status(HttpStatus.CREATED).body(companyOptional.orElseThrow());
//         }
//         return ResponseEntity.notFound().build();
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<?> remove(@PathVariable Long id){
//         Optional <Company> companyOptional= companyService.findById(id);
//         if(companyOptional.isPresent()){
//             companyService.remove(id);
//             return ResponseEntity.ok().build();
//         }
//         return ResponseEntity.notFound().build();
//     }

//     @GetMapping("/search")
// public ResponseEntity<List<Company>> search(@RequestParam("q") String query) {
//     List<Company> companies = companyService.findByCriteria(query);
//     // if (companies.isEmpty()) {
//     //     return ResponseEntity.notFound().build();
//     // }
//     return ResponseEntity.ok(companies);
// }
}
    

