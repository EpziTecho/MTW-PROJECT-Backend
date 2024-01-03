package com.mtwproject.backend.mtwprojectbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import java.util.List;
public interface BookingRepository  extends CrudRepository <Booking, Long>{

    List<Booking> findAll();    
    

}
