package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Company;

public interface CompanyService {

    List <Company> findAll();
    
    Optional<Company> findById(Long id);

    Company save(Company company);

    Optional<Company> update (Company company , Long id);

    void remove (Long id);
    
    List<Company> findByCriteria(String searchCriteria);

    Optional<Company> findByBusinessName(String businessName);
    Optional<Company> findByIdNumber(String idNumber);
}
