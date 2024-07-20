package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Driver;

public interface DriverService {

    List<Driver> findAll();

    Optional<Driver> findById(Long id);

    Driver save(Driver driver);

    Optional<Driver> update(Driver driver, Long id);

    void remove(Long id);

    String deactivateDriver(Long id);

    Optional<Driver> findByIdNumber(String idNumber);
}
