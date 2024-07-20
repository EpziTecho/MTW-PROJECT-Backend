package com.mtwproject.backend.mtwprojectbackend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {

    /*
     * Lo que hace este método es devolver un iterable de conductores ordenados
     * alfabéticamente por el nombre.
     */
    public abstract Iterable<Driver> findAllByOrderByNamesDesc();

    public abstract Optional<Driver> findByIdNumber(String idNumber);

}
