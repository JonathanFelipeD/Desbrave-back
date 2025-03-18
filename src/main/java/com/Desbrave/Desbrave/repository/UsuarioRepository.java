package com.Desbrave.Desbrave.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Desbrave.Desbrave.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);

    
    
}
