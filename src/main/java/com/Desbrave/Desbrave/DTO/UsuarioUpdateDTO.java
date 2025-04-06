package com.Desbrave.Desbrave.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateDTO {
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    

    // Getters e Setters
}
