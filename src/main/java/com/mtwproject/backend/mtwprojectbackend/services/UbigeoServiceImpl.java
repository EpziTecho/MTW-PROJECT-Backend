package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Ubigeo;
import com.mtwproject.backend.mtwprojectbackend.repositories.UbigeoRepository;

@Service
public class UbigeoServiceImpl implements UbigeoService{


    @Autowired
    private UbigeoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Ubigeo> findAll() {
      
        return (List<Ubigeo>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ubigeo> findById(Long id) {
        
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void remove(Long id) {
       
        repository.deleteById(id);
        
    }

    @Override
    @Transactional
    public Ubigeo save(Ubigeo distrit) {
        
        return repository.save(distrit);
    }

    @Override
    @Transactional
    public Optional<Ubigeo> update(Ubigeo ubigeo, Long id) {
        
        Optional<Ubigeo> o = this.findById(id);
        Ubigeo ubigeoOptional=null;
        if(o.isPresent()){
            Ubigeo ubigeoDb= o.orElseThrow();
            ubigeoDb.setIdUbigeo(ubigeo.getIdUbigeo());
            ubigeoDb.setDepartment(ubigeo.getDepartment());
            ubigeoDb.setProvince(ubigeo.getProvince());
            ubigeoDb.setDistrict(ubigeo.getDistrict());
            ubigeoDb.setName(ubigeo.getName());
            ubigeoDb.setUbigeo(ubigeo.getUbigeo());
            ubigeoOptional=this.save(ubigeoDb);
        }
        return Optional.ofNullable(ubigeoOptional);
    }
        
    @Override
    @Transactional(readOnly = true)
    public List<Ubigeo> findByDepartment(String department) {
        return repository.findByDepartment(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ubigeo> findByProvince(String province) {
        return repository.findByProvince(province);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ubigeo> findByDistrict(String district) {
        return repository.findByDistrict(district);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Ubigeo> findByDepartmentAndProvince(String department, String province) {
        return repository.findByDepartmentAndProvince(department, province);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ubigeo> findByDepartmentAndProvinceAndDistrict(String department, String province, String district) {
        return repository.findByDepartmentAndProvinceAndDistrict(department, province, district);
    }
}
