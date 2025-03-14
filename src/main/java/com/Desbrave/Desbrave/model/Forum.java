package com.Desbrave.Desbrave.model;

import java.time.LocalDate;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Forum {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idforum")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "titulo", nullable = false)
    private Titulo titulo;

    @Column(name = "descricao", length = 25, nullable = false)
    private String descricao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "usuario_idusuario", nullable = false)
    private Integer usuarioId;

    // Construtor padrão
    public Forum() {}

    // Construtor com parâmetros
    public Forum(Titulo titulo, String descricao, LocalDate dataCriacao, Integer usuarioId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.usuarioId = usuarioId;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}

