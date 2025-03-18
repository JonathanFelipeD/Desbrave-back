package com.Desbrave.Desbrave.DTO;

import com.Desbrave.Desbrave.constants.TipoUsuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;



public class CadastrarRequest {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private TipoUsuario tipoUsuario;
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

   

}
