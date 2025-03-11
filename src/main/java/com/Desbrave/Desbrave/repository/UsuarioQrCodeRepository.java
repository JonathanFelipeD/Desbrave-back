package com.Desbrave.Desbrave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Desbrave.Desbrave.model.UsuarioQrCode;

public interface UsuarioQrCodeRepository extends JpaRepository<UsuarioQrCode, Long> {
    List<UsuarioQrCode> findByUsuarioId(Long usuarioId); // Método personalizado para buscar por usuarioId

    List<UsuarioQrCode> findByQrCodeId(Long qrCodeId);
}