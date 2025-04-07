package com.Desbrave.Desbrave.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name = "tokens_recuperacao")
public class TokenRecuperacao {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String token;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private LocalDateTime dataExpiracao;
}