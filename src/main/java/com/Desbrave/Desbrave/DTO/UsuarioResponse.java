package com.Desbrave.Desbrave.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponse {
    private UUID id;
    private String nome;
    private String email;
    private long pontuacaoTotal;
}