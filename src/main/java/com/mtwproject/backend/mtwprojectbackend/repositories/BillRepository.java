package com.mtwproject.backend.mtwprojectbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

    /*
     * Lo que hace este método es devolver un iterable de facturas ordenadas por el
     * id de la factura de forma descendente.
     * 
     */
    public abstract Iterable<Bill> findAllByOrderByIdBillDesc();

    @Query("SELECT b FROM Bill b WHERE b.id IN (SELECT bk.bill.id FROM Booking bk WHERE bk.company.id = :idCompany)")
    List<Bill> findBillsByCompanyId(Long idCompany);

}