package com.mtwproject.backend.mtwprojectbackend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Company;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    List<Booking> findAll();

    List<Booking> findByCompany(Company company);

    @Query("SELECT b FROM Booking b WHERE (:idBooking IS NULL OR b.idBooking = :idBooking) " +
            "AND (:applicant = '' OR b.applicant = :applicant) " +
            "AND (:idCompany IS NULL OR b.company.id = :idCompany) " +
            "AND (:idPassenger IS NULL OR b.passenger.id = :idPassenger) " +
            "AND (:idDriver IS NULL OR b.driver.id = :idDriver)")
    List<Booking> listBookingsByParams(Long idBooking, String applicant, Long idCompany, Long idPassenger,
            Long idDriver);

}
