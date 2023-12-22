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
@Table(name = "COMPANY")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompany;

    private String businessName;

    private String idNumber;

    private String adress;

    private String tradeName;

    private String phone;
    
}
