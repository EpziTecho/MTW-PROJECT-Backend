package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Accounting;
import com.mtwproject.backend.mtwprojectbackend.repositories.AccountingRepository;

@Service
public class AccountingServiceImpl implements AccountingService {

    @Autowired
    private AccountingRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Accounting> findAll() {
        return (List<Accounting>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Accounting> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Accounting save(Accounting accounting) {
        return repository.save(accounting);
    }

    @Override
    @Transactional
    public Optional<Accounting> update(Accounting accounting, Long id) {
        Optional<Accounting> o = this.findById(id);
        Accounting accountingOptional = null;
        if (o.isPresent()) {
            Accounting accountingDb = o.orElseThrow();
            accountingDb.setIdBooking(accounting.getIdBooking());
            accountingDb.setDate(accounting.getDate());
            accountingDb.setTime(accounting.getTime());
            accountingDb.setStatus(accounting.getStatus());
            accountingDb.setNotes(accounting.getNotes());
            accountingOptional = this.save(accountingDb);

        }
        return Optional.ofNullable(accountingOptional);
    }

}
