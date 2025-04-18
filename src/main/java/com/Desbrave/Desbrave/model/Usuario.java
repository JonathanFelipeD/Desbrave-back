package com.Desbrave.Desbrave.model;



import java.time.LocalDate;
import java.util.Collection;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.Desbrave.Desbrave.constants.TipoUsuario;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;

    
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private TipoUsuario tipoUsuario; 
    
    private LocalDate dataCriacao;
    private long pontuacaoTotal;

    private String resetToken;
    private LocalDate dataExpiracaoToken;

    public Usuario(String nome,String email, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }
    public String getRoleAsAuthority() {
        return "ROLE_" + this.tipoUsuario; 
    }
     



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         if(this.tipoUsuario == TipoUsuario.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER")); 
         else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
    @Override
    public String getPassword() {
       return senha;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }



    

    
}



