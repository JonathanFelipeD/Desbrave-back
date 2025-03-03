package com.Desbrave.Desbrave.model;



import java.time.LocalDate;
import java.util.Date;

import com.Desbrave.Desbrave.constants.tipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nomeCompleto;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 40)
    private String senha;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_Nascimento",nullable = false)
    private Date dataNascimento;

    @Column(nullable = false)
    private Enum<tipoUsuario> tipoUsuario; 

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate dataCriacao;


    @Column(nullable = true)
    private long pontuacaoTotal;




    
}



