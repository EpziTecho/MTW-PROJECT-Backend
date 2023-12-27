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
@Table(name = "UBIGEO")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Ubigeo {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUbigeo;
    private String department;
    private String province;
    private String district;
    private String name;
    private String ubigeo;
   



    
}
