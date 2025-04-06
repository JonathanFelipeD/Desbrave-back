package com.Desbrave.Desbrave.DTO;

import java.time.LocalDateTime;

import com.Desbrave.Desbrave.model.Forum;




public record ForumResponse(
    Long id,
    String titulo,
    String descricao,
    LocalDateTime dataCriacao,
    Long usuarioId
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