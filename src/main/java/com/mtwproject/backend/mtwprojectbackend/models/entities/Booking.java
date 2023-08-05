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
@Table(name = "SERVICE")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Booking {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_service;
    private String date;
    private String time;
    private Long id_company;
    private Long id_area;
    private Long id_passenger;
    private String origen;
    private Long id_distrito_origen;
    private String destino;
    private Long id_distrito_destino;
    private String notes;
    private String tarifa;
    private Long id_conductor;
    private String status;
    private Long id_factura;
    
    
}