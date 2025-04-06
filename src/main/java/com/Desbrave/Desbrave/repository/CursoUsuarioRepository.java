package com.Desbrave.Desbrave.repository;

import com.Desbrave.Desbrave.model.CursoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CursoUsuarioRepository extends JpaRepository<CursoUsuario, Long> {
    List<CursoUsuario> findByUsuarioId(Long usuarioId);
    int countByUsuarioId(Long usuarioId);
}
