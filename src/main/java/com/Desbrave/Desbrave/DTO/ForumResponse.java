package com.Desbrave.Desbrave.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import com.Desbrave.Desbrave.model.Forum;




public record ForumResponse(
    UUID id,
    String titulo,
    String descricao,
    LocalDateTime dataCriacao,
    UUID usuarioId
) {
    
    public ForumResponse(Forum forum) {
        this(
            forum.getId(),
            forum.getTitulo().name(), 
            forum.getDescricao(),
            forum.getDataCriacao(),
            forum.getUsuario() != null ? forum.getUsuario().getId() : null
        );
    }
}