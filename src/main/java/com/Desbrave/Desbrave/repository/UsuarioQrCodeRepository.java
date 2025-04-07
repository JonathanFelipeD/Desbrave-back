package com.Desbrave.Desbrave.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Desbrave.Desbrave.model.UsuarioQrCode;

public interface UsuarioQrCodeRepository extends JpaRepository<UsuarioQrCode, UUID> {
    List<UsuarioQrCode> findByUsuarioId(UUID usuarioId); 
    List<UsuarioQrCode> findByQrCodeId(UUID qrCodeId);

     
    boolean existsByUsuarioIdAndQrCodeId(UUID usuarioId, UUID qrCodeId);

    @Query("SELECT SUM(u.pontosGanhos) FROM UsuarioQrCode u WHERE u.usuario.id = :usuarioId")
    Optional<Long> sumPontosGanhosByUsuarioId(@Param("usuarioId") UUID usuarioId);

    Optional<UsuarioQrCode> findTopByUsuarioIdOrderByDataEscaneamentoDesc(UUID usuarioId);
}