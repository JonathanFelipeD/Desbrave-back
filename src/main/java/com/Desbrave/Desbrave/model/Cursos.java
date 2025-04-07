package com.Desbrave.Desbrave.model;
import java.util.UUID;

import com.Desbrave.Desbrave.constants.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @Table
    public class Cursos {
    
        @Id
        @GeneratedValue
        @Column(name = "idcursos")
        private UUID idcursos;
    
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
    
    
    
    
