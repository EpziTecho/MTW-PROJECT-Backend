package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Bill;
import com.mtwproject.backend.mtwprojectbackend.repositories.BillRepository;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository repository;

    private static final String PENDING_STATUS = "Activo";
    private static final String INACTIVE_STATUS = "Inactivo";

    @Override
    @Transactional(readOnly = true)
    public List<Bill> findAll() {
        return (List<Bill>) repository.findAll();
    }

    @Override
    public Optional<Bill> findById(Long id) {

        return repository.findById(id);
    }

    @Override
    public Bill save(Bill bill) {

        // manejo de activo e inactivo para el eliminado logico
        if (bill.getIdBill() != null) {
            if (INACTIVE_STATUS.equals(bill.getStatus())) {
                bill.setStatus(INACTIVE_STATUS);
            } else {
                bill.setStatus(PENDING_STATUS);
            }
        } else {
            bill.setStatus(PENDING_STATUS);
        }

        return repository.save(bill);
    }

}
