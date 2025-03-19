package com.Desbrave.Desbrave.model;



import java.time.LocalDate;
import java.util.Collection;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.Desbrave.Desbrave.constants.TipoUsuario;




@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String nomeCompleto;
    private String email;
    private String senha;
     private LocalDate dataNascimento;
    private TipoUsuario tipoUsuario; 
    private LocalDate dataCriacao;
    private long pontuacaoTotal;

    
   

    public Usuario(String email, String senha, TipoUsuario tipoUsuario) {
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }
     

    public Usuario(Long id, String nomeCompleto, String email, String senha, LocalDate dataNascimento,
            TipoUsuario tipoUsuario, LocalDate dataCriacao, long pontuacaoTotal) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.tipoUsuario = tipoUsuario;
        this.dataCriacao = dataCriacao;
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public Usuario() {
    }


    

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setPontuacaoTotal(long pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public long getPontuacaoTotal() {
        return pontuacaoTotal;
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



