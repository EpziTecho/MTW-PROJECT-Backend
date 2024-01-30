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
@Table(name = "DRIVER")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDriver;
    private String names;
    private String lastNames;
    private String idNumber;
    private String phone;
    private String status;
    private String model;
    private String brand;
    private String carPlate;
    private String year;
    private String color;


}
