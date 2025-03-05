package com.Desbrave.Desbrave.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario_QrCode")
public class UsuarioQrCode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private LocalDateTime dataEscaneamento;

    @Column(nullable = true)
    private Long pontosGanhos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // @ManyToOne
    // @JoinColumn(name = "qr_code_id")
    // private QrCode qrCode;





}
