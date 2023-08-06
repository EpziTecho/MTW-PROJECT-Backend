package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Currency;
import com.mtwproject.backend.mtwprojectbackend.repositories.CurrencyRepository;

@Service    
public class CurrencyServiveImpl implements CurrencyService {
    
    @Autowired
    private CurrencyRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Currency> findAll() {
        return (List<Currency>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Currency> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional
    public Currency save(Currency currency) {
        return repository.save(currency);
    }

    @Override
    @Transactional
    public Optional<Currency> update(Currency currency, Long id) {
        Optional<Currency> o = this.findById(id);
        Currency currencyOptional=null;
        if(o.isPresent()){
            Currency currencyDb= o.orElseThrow();
            currencyDb.setName(currency.getName());
            currencyOptional=this.save(currencyDb);
        }
        return Optional.ofNullable(currencyOptional);
    }
    

}
