package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Ubigeo;

public interface UbigeoService {

    List <Ubigeo> findAll();

    Optional<Ubigeo> findById(Long id);

    Ubigeo save(Ubigeo distrit);

    Optional<Ubigeo> update (Ubigeo distrit , Long id);

    void remove (Long id);

    
    
    
}
