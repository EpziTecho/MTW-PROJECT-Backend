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
@Table(name = "BOOKING")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Booking {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBooking;
    private String date;
    private String time;
    private Long idCompany;
    private Long idArea;
    private Long idPassenger;
    private String pickUp;
    private Long idDistritPickUp;
    private String destination;
    private Long idDistritDestination;
    private String notes;
    private String price;
    private Long idDriver;
    private String status;
    private Long idBill;
    
    
}