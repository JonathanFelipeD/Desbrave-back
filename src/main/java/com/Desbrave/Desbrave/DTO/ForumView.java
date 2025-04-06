package com.Desbrave.Desbrave.DTO;

import java.time.LocalDateTime;


public interface ForumView {
     // Campos diretos
     Long getId();
     String getTitulo();
     String getDescricao();
     LocalDateTime getDataCriacao();
     
     // Projeção aninhada para usuário
     UsuarioView getUsuario();
     
     interface UsuarioView {
         Long getId();
         String getNome();
     }
}