package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;


import com.mtwproject.backend.mtwprojectbackend.models.entities.Area;

public interface AreaService {

    List<Area> findAll();

    Optional<Area> findById(Long id);

    Area save(Area area);

    Optional<Area> update (Area area , Long id);

    void remove (Long id);


    
}
