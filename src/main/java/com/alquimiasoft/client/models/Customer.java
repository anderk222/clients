package com.alquimiasoft.client.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;
    
    @Column(length = 13, nullable = false, unique = true)
    @Size(max = 13, min = 10,message = "identification must have between 10 and 13 characters")
    private String identificationNumber;
    
    @Column(length = 255, nullable = false )
    @Max(value = 255, message = "name too longer")
    @Min(value=2, message = "name too short")
    private String names;
    
    @Column(length = 255, unique = true)
    @Email
    private String email;

    @Column(length = 10)
    @Size(min = 10, max = 10, message = "phone must have 10 characters")
    private String  phone;
  
    
    public Customer(String names, String email, String phone){
        
        this.names= names;
        this.email=email;
        this.phone = phone;
        
    }
    
    
}
