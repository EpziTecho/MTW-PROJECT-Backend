package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Distrit;
import com.mtwproject.backend.mtwprojectbackend.repositories.DistritRepository;

@Service
public class DistritServiceImpl implements DistritService{


    @Autowired
    private DistritRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Distrit> findAll() {
      
        return (List<Distrit>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Distrit> findById(Long id) {
        
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void remove(Long id) {
       
        repository.deleteById(id);
        
    }

    @Override
    @Transactional
    public Distrit save(Distrit distrit) {
        
        return repository.save(distrit);
    }

    @Override
    @Transactional
    public Optional<Distrit> update(Distrit distrit, Long id) {
        
        Optional<Distrit> o = this.findById(id);
        Distrit distritOptional=null;
        if(o.isPresent()){
            Distrit distritDb= o.orElseThrow();
            distritDb.setName(distrit.getName());
            distritOptional=this.save(distritDb);
        }
        return Optional.ofNullable(distritOptional);
    }
        
    
}
