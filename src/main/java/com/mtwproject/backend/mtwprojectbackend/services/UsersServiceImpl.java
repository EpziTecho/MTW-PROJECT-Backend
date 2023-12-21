package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Users;
import com.mtwproject.backend.mtwprojectbackend.repositories.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Users> findAll() {
        return (List<Users>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Users> findById(Long id) {
        return repository.findById(id);    
    }

    @Override
    @Transactional
    public Users save(Users user) {
        return repository.save(user);
    }

    @Override
    public Optional<Users> update(Users user, Long id) {
        Optional<Users> o = repository.findById(id);
        Users userOptional=null;
        if(o.isPresent()) {
            Users userDb=o.orElseThrow();
            userDb.setUsername(user.getUsername());
            userDb.setPassword(user.getPassword());
            userDb.setStatus(user.getStatus());
            userDb.setIdUserType(user.getIdUserType());
            userOptional=repository.save(userDb);
           
        }
        return Optional.ofNullable(userOptional);
                
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
        
    }
    
}
