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
import jakarta.validation.constraints.Pattern;



@Entity 
@Table(name = "COMPANY")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompany;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    @Pattern(regexp = "^[0-9]{11}$", message = "El número de identificación debe tener 11 dígitos")
    private String idNumber;

    @Column(nullable = false)
    private String address;

    private String tradeName;

    private String phone;
    
}
