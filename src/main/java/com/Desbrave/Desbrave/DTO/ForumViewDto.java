package com.Desbrave.Desbrave.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ForumViewDto {
    UUID getId();
    String getTitulo();
    String getDescricao();
    LocalDateTime getDataCriacao();
}


