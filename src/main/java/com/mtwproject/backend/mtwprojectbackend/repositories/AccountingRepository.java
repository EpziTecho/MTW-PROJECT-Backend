package com.mtwproject.backend.mtwprojectbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Accounting;

public interface AccountingRepository extends CrudRepository <Accounting, Long> {
    
}
