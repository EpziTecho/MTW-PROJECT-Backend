package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Area;
import com.mtwproject.backend.mtwprojectbackend.repositories.AreaRepository;


@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Area> findAll() {
    
        return (List<Area>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Area> findById(Long id) {
       
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void remove(Long id) {
       repository.deleteById(id);
    }

    @Override
    @Transactional
    public Area save(Area area) {
        return repository.save(area);
    }

    @Override
    @Transactional
    public Optional<Area> update(Area area, Long id) {
        Optional<Area> o = this.findById(id);
        Area areaOptional=null;
        if(o.isPresent()){
            Area areaDb= o.orElseThrow();
            areaDb.setName(area.getName());
            areaOptional=this.save(areaDb);
        }
        return Optional.ofNullable(areaOptional);
    }
    
    

}
