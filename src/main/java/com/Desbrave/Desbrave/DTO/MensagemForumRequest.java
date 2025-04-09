package com.Desbrave.Desbrave.DTO;

import com.Desbrave.Desbrave.constants.Titulo;
import lombok.Data;

import java.util.UUID;

@Data
public class MensagemForumRequest {
    private UUID usuarioId;
    private Titulo tituloForum;
    private String conteudo;
}


