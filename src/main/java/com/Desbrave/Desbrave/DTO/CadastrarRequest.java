package com.Desbrave.Desbrave.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarRequest {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private String tipoUsuario;

    @NotNull
    private LocalDate dataNascimento; // Pode ser nulo

   
    
}