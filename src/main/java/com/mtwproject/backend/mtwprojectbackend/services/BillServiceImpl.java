package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Bill;
import com.mtwproject.backend.mtwprojectbackend.repositories.BillRepository;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository repository;    


    @Override
    @Transactional(readOnly = true)
    public List<Bill> findAll() {
              return (List<Bill>) repository.findAll();
    }
    
}

