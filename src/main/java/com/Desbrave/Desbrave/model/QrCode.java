package com.Desbrave.Desbrave.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class QrCode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String codigo;

    @OneToMany(mappedBy = "qrCode", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<UsuarioQrCode> usuarioQrCodes;

}
