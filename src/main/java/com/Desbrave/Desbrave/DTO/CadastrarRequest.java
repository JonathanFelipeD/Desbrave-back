package com.Desbrave.Desbrave.DTO;

import com.Desbrave.Desbrave.constants.TipoUsuario;

public class CadastrarRequest {
    private String email;
    private String senha;
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
