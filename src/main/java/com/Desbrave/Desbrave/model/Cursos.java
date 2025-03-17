package com.Desbrave.Desbrave.model;
import com.Desbrave.Desbrave.constants.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cursos")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcursos")
    private int idcursos;

    @Column(nullable = true, length = 45)
    private String titulo;

    @Column(nullable = true, length = 150)
    private String descricao;

    @Column(nullable = true, length = 45)
    private String categoria;

    @Column(nullable = true)
    private Integer cargaHoraria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Status status; 

    @Column(name = "url_externa", nullable = false, length = 255)
    private String urlExterna;


    
}



