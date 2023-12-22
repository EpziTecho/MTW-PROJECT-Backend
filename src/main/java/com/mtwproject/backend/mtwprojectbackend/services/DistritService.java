package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Distrit;

public interface DistritService {

    List <Distrit> findAll();

    Optional<Distrit> findById(Long id);

    Distrit save(Distrit distrit);

    Optional<Distrit> update (Distrit distrit , Long id);

    void remove (Long id);

    
    
    
}
