package com.Desbrave.Desbrave.DTO;

import java.time.LocalDate;
import java.util.UUID;

import com.Desbrave.Desbrave.constants.Titulo;

import lombok.Data;

@Data
public class PostagemResponse {
    private UUID id;
    private String conteudo;
    private LocalDate dataPostagem;
    private String nomeUsuario;
    private String tituloForum;
    public void setTituloForum(Titulo titulo) {
        this.tituloForum = titulo.getTitulo();
    }
}