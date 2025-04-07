package com.Desbrave.Desbrave.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Desbrave.Desbrave.model.UsuarioQrCode;

public interface UsuarioQrCodeRepository extends JpaRepository<UsuarioQrCode, UUID> {
    List<UsuarioQrCode> findByUsuarioId(UUID usuarioId); 
    List<UsuarioQrCode> findByQrCodeId(UUID qrCodeId);
}