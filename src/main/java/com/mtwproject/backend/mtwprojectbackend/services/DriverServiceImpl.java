package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Driver;
import com.mtwproject.backend.mtwprojectbackend.repositories.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {
    
    @Autowired
    private DriverRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Driver> findAll() {
        return (List<Driver>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Driver> findById(Long id) {
       
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional
    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    @Override
    @Transactional
    public Optional<Driver> update(Driver driver, Long id) {
        Optional<Driver> o = this.findById(id);
        Driver driverOptional=null;
        if(o.isPresent()){
            Driver driverDb= o.orElseThrow();
            driverDb.setNames(driver.getNames());
            driverDb.setLastNames(driver.getLastNames());
            driverDb.setIdNumber(driver.getIdNumber());
            driverDb.setPhone(driver.getPhone());
            driverDb.setModel(driver.getModel());
            driverDb.setBrand(driver.getBrand());
            driverDb.setCarPlate(driver.getCarPlate());
            driverDb.setYear(driver.getYear());
            driverDb.setColor(driver.getColor());
            driverOptional=this.save(driverDb);
        }
        return Optional.ofNullable(driverOptional);
    }
    
}
