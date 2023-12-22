package com.mtwproject.backend.mtwprojectbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Currency;

public interface CurrencyRepository extends CrudRepository <Currency, Long> {
    
}
