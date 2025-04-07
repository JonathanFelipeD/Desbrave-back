package com.Desbrave.Desbrave.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Desbrave.Desbrave.model.HistoricoResgate;


public interface HistoricoResgateRepository extends JpaRepository<HistoricoResgate, UUID> {
    List<HistoricoResgate> findByUsuarioId(UUID usuarioId);


}

