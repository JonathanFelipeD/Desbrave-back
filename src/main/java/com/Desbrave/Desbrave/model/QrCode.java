package com.Desbrave.Desbrave.model;


import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class QrCode {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    private String codigo;

    private Long pontos;

}
