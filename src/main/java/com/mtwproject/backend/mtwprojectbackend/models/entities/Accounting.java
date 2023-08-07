package com.mtwproject.backend.mtwprojectbackend.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ACCOUNTING")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Accounting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_accounting;
    private Long id_service;
    private String date;
    private String time;
    private String status;
    private String notes;
}
