package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Bill;

public interface BillService {

    List<Bill> findAll();
    
    
}