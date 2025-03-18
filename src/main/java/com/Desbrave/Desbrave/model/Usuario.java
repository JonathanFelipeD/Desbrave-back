package com.Desbrave.Desbrave.model;



import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.Desbrave.Desbrave.constants.TipoUsuario;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nomeCompleto;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(nullable = false, length = 40)
    private String senha;
    
    @Column(name = "data_Nascimento",nullable = false)
    private Date dataNascimento;
    @Column(nullable = false)
    private TipoUsuario tipoUsuario; 
    @Column(nullable = false)
    private LocalDate dataCriacao;
    @Column
    private long pontuacaoTotal;
    //data automatico
    @PrePersist
    protected void onCreate(){
        this.dataCriacao = LocalDate.now();
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



