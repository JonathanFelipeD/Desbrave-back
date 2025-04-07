package com.Desbrave.Desbrave.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostagemRequest {
    private String conteudo;
    private UUID usuarioId;
    private UUID forumId;
}
