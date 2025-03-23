package com.Desbrave.Desbrave.constants;

public enum TipoUsuario{   
    ADMIN("ADMIN"),
    USUARIO_COMUM("USUARIO_COMUM");   
        
      

    private String tipoUsuario;

    TipoUsuario(String tipoUsuario){
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario(){
        return tipoUsuario;
    }
      
}