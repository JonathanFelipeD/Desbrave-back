package com.Desbrave.Desbrave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Desbrave.Desbrave.model.HistoricoResgate;


public interface HistoricoResgateRepository extends JpaRepository<HistoricoResgate, Long> {
    List<HistoricoResgate> findByUsuarioId(Long usuarioId);


}

