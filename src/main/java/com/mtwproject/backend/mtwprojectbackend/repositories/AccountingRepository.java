package com.mtwproject.backend.mtwprojectbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Accounting;

public interface AccountingRepository extends JpaRepository<Accounting, Long> {

    Iterable<Accounting> findAllByOrderByIdAccountingDesc();
}
