package com.Desbrave.Desbrave.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Desbrave.Desbrave.model.QrCode;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.model.UsuarioQrCode;
import com.Desbrave.Desbrave.repository.QrCodeRepository;
import com.Desbrave.Desbrave.repository.UsuarioQrCodeRepository;
import com.Desbrave.Desbrave.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioQrCodeService {

    
    private final UsuarioRepository usuarioRepository;

    
    private final QrCodeRepository qrCodeRepository;

    
    private final UsuarioQrCodeRepository usuarioQrCodeRepository;

    public UsuarioQrCode associarUsuarioQrCode(Long usuarioId, Long qrCodeId) {
        // Buscar o usuário e o QR code pelos seus IDs
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        QrCode qrCode = qrCodeRepository.findById(qrCodeId).orElseThrow(() -> new RuntimeException("QR Code não encontrado"));

        // Criar a associação
        UsuarioQrCode usuarioQrCode = new UsuarioQrCode();
        usuarioQrCode.setUsuario(usuario);
        usuarioQrCode.setQrCode(qrCode);
        usuarioQrCode.setDataEscaneamento(LocalDateTime.now());

        return usuarioQrCodeRepository.save(usuarioQrCode);
    }

    public List<UsuarioQrCode> listarAssociacoes() {
        return usuarioQrCodeRepository.findAll();
    }

    public List<UsuarioQrCode> buscarAssociacoesPorUsuarioId(Long usuarioId) {
        return usuarioQrCodeRepository.findByUsuarioId(usuarioId);
    }

    public List<UsuarioQrCode> buscarAssociacoesPorQrCodeId(Long qrCodeId) {
        return usuarioQrCodeRepository.findByQrCodeId(qrCodeId);
    }
}
