package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Accounting;

public interface AccountingService {

    List<Accounting> findAll();

    Optional<Accounting> findById(Long id);

    Accounting save(Accounting accounting);

    Optional<Accounting> updateStatus(Accounting accounting, Long id);

    Optional<Accounting> update(Accounting accounting, Long id);

    void remove(Long id);

}
