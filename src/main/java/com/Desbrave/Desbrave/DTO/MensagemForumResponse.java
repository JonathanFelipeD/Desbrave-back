package com.Desbrave.Desbrave.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MensagemForumResponse {
    private String nomeUsuario;
    private String conteudo;
    private LocalDateTime dataEnvio;
}
