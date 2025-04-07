package com.Desbrave.Desbrave.DTO;

import java.time.LocalDate;
import java.util.UUID;


import lombok.Data;

@Data
public class PostagemResponse {
    private UUID id;
    private String conteudo;
    private LocalDate dataPostagem;
    private String nomeUsuario;
    private String tituloForum;
    public void setTituloForum(String titulo) {
        this.tituloForum = titulo;
    }
}