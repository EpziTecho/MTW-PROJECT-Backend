package com.mtwproject.backend.mtwprojectbackend.services;
import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Currency;

public interface CurrencyService {

    List <Currency> findAll();

    Optional<Currency> findById(Long id);

    Currency save(Currency currency);

    Optional<Currency> update (Currency currency , Long id);

    void remove (Long id);
    
    
}
