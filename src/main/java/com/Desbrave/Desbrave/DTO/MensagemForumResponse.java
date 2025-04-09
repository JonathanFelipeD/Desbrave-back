package com.Desbrave.Desbrave.DTO;


import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MensagemForumResponse {
    private UUID id;
    private String nomeUsuario;
    private String conteudo;
    private LocalDateTime dataEnvio;
    private String tituloForum;
}



