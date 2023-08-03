package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Passenger;
import com.mtwproject.backend.mtwprojectbackend.repositories.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Passenger> findAll() {
        return (List<Passenger>) repository.findAll();
    }
}