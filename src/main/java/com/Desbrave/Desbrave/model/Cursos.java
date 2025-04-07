package com.Desbrave.Desbrave.model;

import java.util.UUID;

import com.Desbrave.Desbrave.constants.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Cursos") // Nome da tabela especificado
public class Cursos {

    @Id
    @GeneratedValue
    @Column(name = "idcursos")
    private UUID idcursos;

    @Column(nullable = false, length = 45) // melhor evitar campos nulos em campos obrigatórios
    private String titulo;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(nullable = false, length = 45)
    private String categoria;

    @Column(nullable = false)
    private Integer cargaHoraria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "url_externa", nullable = false, length = 255)
    private String urlExterna;
}
