package com.Desbrave.Desbrave.repository;

import com.Desbrave.Desbrave.model.CursoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CursoUsuarioRepository extends JpaRepository<CursoUsuario, UUID> {
    List<CursoUsuario> findByUsuarioId(UUID usuarioId);
    int countByUsuarioId(UUID usuarioId);
}
