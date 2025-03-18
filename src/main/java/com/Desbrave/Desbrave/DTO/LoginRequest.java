package com.Desbrave.Desbrave.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public class LoginRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String senha;
    
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
    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

   


    
}