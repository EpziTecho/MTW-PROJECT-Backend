package com.mtwproject.backend.mtwprojectbackend.models.entities;

import jakarta.persistence.Column;
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
@Table(name = "BOOKING")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Booking {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBooking;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private Long idCompany;

    private String applicant;

    private Long idArea;

    @Column(nullable = false)
    private Long idPassenger;
   
    @Column(nullable = false)
    private String pickUp;

    @Column(nullable = false)
    private Long idDistritPickUp;
    
    @Column(nullable = false)
    private String destination;
    
    @Column(nullable = false)
    private Long idDistritDestination;
    
    @Column(nullable = false)
    private String notes;
    
    @Column(nullable = false)
    private Double price;

    private Long idDriver;

    private String status;

    private Long idBill;
    
    
}