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

    private static final String ACTIVE_STATUS = "Activo";
    private static final String INACTIVE_STATUS = "Inactivo";

    @Autowired
    private DriverRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Driver> findAll() {
        return (List<Driver>) repository.findAllByOrderByNamesDesc();
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
    public String deactivateDriver(Long id) {
        return repository.findById(id).map(driver -> {
            driver.setStatus(INACTIVE_STATUS);
            repository.save(driver);
            return "Conductor desactivado";
        }).orElseThrow(() -> new RuntimeException("No se pudo desactivar el conductor"));
    }

    @Override
    @Transactional
    public Driver save(Driver driver) {
        driver.setStatus(ACTIVE_STATUS);
        return repository.save(driver);
    }

    @Override
    @Transactional
    public Optional<Driver> update(Driver driver, Long id) {
        Optional<Driver> o = this.findById(id);
        Driver driverOptional = null;
        if (o.isPresent()) {
            Driver driverDb = o.orElseThrow();
            driverDb.setNames(driver.getNames());
            driverDb.setLastNames(driver.getLastNames());
            driverDb.setIdNumber(driver.getIdNumber());
            driverDb.setPhone(driver.getPhone());
            driverDb.setStatus(driver.getStatus());
            driverDb.setModel(driver.getModel());
            driverDb.setBrand(driver.getBrand());
            driverDb.setCarPlate(driver.getCarPlate());
            driverDb.setYear(driver.getYear());
            driverDb.setColor(driver.getColor());
            driverOptional = this.save(driverDb);
        }
        return Optional.ofNullable(driverOptional);
    }

    @Override
    public Optional<Driver> findByIdNumber(String idNumber) {
        Optional<Driver> o = repository.findByIdNumber(idNumber);

        return o;

    }

}
