package com.Desbrave.Desbrave.DTO;

import lombok.Data;

@Data
public class PostagemRequest {
    private String conteudo;
    private Long usuarioId;
    private Long forumId;
}
