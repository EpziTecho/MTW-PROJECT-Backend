package com.mtwproject.backend.mtwprojectbackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Company;

public interface CompanyRepository extends CrudRepository<Company, Long>{
    List<Company> findByBusinessNameContainingOrIdNumberContainingOrPhoneContaining(String businessName, String idNumber, String phone);
    Optional<Company> findByBusinessName(String businessName);
    Optional<Company> findByIdNumber(String idNumber);
}

