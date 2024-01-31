package com.mtwproject.backend.mtwprojectbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {

    /*
     * Lo que hace este método es devolver un iterable de facturas ordenadas por el
     * id de la factura de forma descendente.
     * 
     */
    public abstract Iterable<Bill> findAllByOrderByIdBillDesc();
}