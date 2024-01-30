package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Bill;

public interface BillService {

    public abstract List<Bill> findAll();

    public abstract Optional<Bill> findById(Long id);

    public abstract Bill save(Bill bill);

}