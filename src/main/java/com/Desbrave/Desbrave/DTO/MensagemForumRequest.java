package com.Desbrave.Desbrave.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class MensagemForumRequest {
    private UUID usuarioId;
    private String tituloForum;
    private String conteudo;
}


