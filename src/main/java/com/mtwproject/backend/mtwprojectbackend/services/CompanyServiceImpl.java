package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Company;
import com.mtwproject.backend.mtwprojectbackend.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
    
    @Autowired
    private CompanyRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Company> findAll() {
       return (List<Company>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Company> findById(Long id) {
       return repository.findById(id);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional
    public Company save(Company company) {
     return repository.save(company);
    }

    @Override
    @Transactional
    public Optional<Company> update(Company company, Long id) {
        
        Optional<Company> o = this.findById(id);
        Company companyOptional=null;
        if(o.isPresent()){
            Company companyDb= o.orElseThrow();
            companyDb.setBusiness_name(company.getBusiness_name());
            companyDb.setIdnumber(company.getIdnumber());
            companyDb.setAdress(company.getAdress());
            companyDb.setTradename(company.getTradename());
            companyDb.setPhone(company.getPhone());
            companyOptional=this.save(companyDb);
        }
        return Optional.ofNullable(companyOptional);
    }

    
}
