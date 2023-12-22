package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Passenger;
import com.mtwproject.backend.mtwprojectbackend.repositories.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Passenger> findAll() {
        return (List<Passenger>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Passenger> findById(Long id) {
       return repository.findById(id);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional
    public Passenger save(Passenger passenger) {
       return repository.save(passenger);
    }

    @Override
    @Transactional
    public Optional<Passenger> update(Passenger passenger, Long id) {
        Optional <Passenger> o = this.findById(id);
        Passenger passengerOptional=null;
        if( o.isPresent()){
            Passenger passengerDb= o.orElseThrow();
            passengerDb.setNames(passenger.getNames());
            passengerDb.setLastNames(passenger.getLastNames());
            passengerDb.setIdDistrit(passenger.getIdDistrit());
            passengerDb.setPhone(passenger.getPhone());
            passengerDb.setAdress(passenger.getAdress());
            passengerDb.setPhone(passenger.getPhone());

            passengerOptional= this.save(passengerDb);            
        }

        return Optional.ofNullable(passengerOptional);

    }
}