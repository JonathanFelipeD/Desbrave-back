package com.Desbrave.Desbrave.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Desbrave.Desbrave.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);
    Usuario findByResetToken(String token);
}
