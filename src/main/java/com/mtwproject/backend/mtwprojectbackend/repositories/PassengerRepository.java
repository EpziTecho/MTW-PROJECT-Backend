package com.mtwproject.backend.mtwprojectbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Passenger;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {

    /*
     * Lo que hace este método es devolver un iterable de pasajeros ordenados
     * alfabéticamente por el nombre.
     */
    public abstract Iterable<Passenger> findAllByOrderByNamesDesc();
}