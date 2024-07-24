package com.mtwproject.backend.mtwprojectbackend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Bill;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Booking;
import com.mtwproject.backend.mtwprojectbackend.models.entities.Company;

import java.sql.Time;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

        List<Booking> findAll();

        List<Booking> findByCompany(Company company);

        List<Booking> findByBill(Bill bill);

        @Query("SELECT b FROM Booking b WHERE b.bill.id = :billId")
        List<Booking> findByBillId(@Param("billId") Long billId);

        @Query("SELECT b FROM Booking b WHERE (:idBooking IS NULL OR b.idBooking = :idBooking) " +
                        "AND (:applicant = '' OR b.applicant = :applicant) " +
                        "AND (:idCompany IS NULL OR b.company.id = :idCompany) " +
                        "AND (:idPassenger IS NULL OR b.passenger.id = :idPassenger) " +
                        "AND (:idDriver IS NULL OR b.driver.id = :idDriver)" +
                        "AND (:idCurrency IS NULL OR b.currency.id = :idCurrency)")
        List<Booking> listBookingsByParams(Long idBooking, String applicant, Long idCompany, Long idPassenger,
                        Long idDriver, Long idCurrency);

        /*
         * Lo que hace este método es devolver un iterable de reservas ordenadas por el
         * id de la reserva de forma descendente.
         */
        Iterable<Booking> findAllByOrderByIdBookingDesc();

        @Query("SELECT b FROM Booking b WHERE b.bill IS NULL")
        List<Booking> findBookingsWithoutBill();

        @Query("SELECT b FROM Booking b WHERE b.driver.idDriver = ?1 ORDER BY b.date DESC, b.time DESC")
        public abstract List<Booking> findBookingsByDriverAndPageable(Long idDriver, Pageable pageable);

        @Query("SELECT b FROM Booking b " +
                        "WHERE b.driver.idDriver = :idDriver " +
                        "AND (b.date > CURRENT_DATE OR " +
                        "(b.date = CURRENT_DATE AND b.time >= :timeWithTolerance)) " +
                        "ORDER BY b.date ASC, b.time ASC")
        List<Booking> findBookingsByDriverTimeWithToleranceAndPageable(@Param("idDriver") Long idDriver,
                        @Param("timeWithTolerance") Time timeWithTolerance, Pageable pageable);
}
