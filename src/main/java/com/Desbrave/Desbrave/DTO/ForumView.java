package com.Desbrave.Desbrave.DTO;

import java.time.LocalDateTime;
import java.util.UUID;


public interface ForumView {
     UUID getId();
     String getTitulo();
     String getDescricao();
     LocalDateTime getDataCriacao();
     
     UsuarioView getUsuario();
     
     interface UsuarioView {
        UUID getId();
        String getNome();
     }
}