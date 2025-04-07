package com.Desbrave.Desbrave.DTO;



import java.util.UUID;

import com.Desbrave.Desbrave.constants.Titulo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@NoArgsConstructor      
@AllArgsConstructor
@Getter
@Setter
public class ForumRequest {
    @NotBlank
    private String titulo; // Remove o "tituloEnum"

    private String descricao;

    @NotNull
    private UUID usuarioId; // Troque de "int" para "Long"

    // Converte String para ENUM
    public String getTitulo() {
        return Titulo.valueOf(titulo.toUpperCase());
    }
}
