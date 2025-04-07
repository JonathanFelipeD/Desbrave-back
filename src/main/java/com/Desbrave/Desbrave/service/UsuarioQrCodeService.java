package com.Desbrave.Desbrave.service;

import com.Desbrave.Desbrave.DTO.*;
import com.Desbrave.Desbrave.model.*;
import com.Desbrave.Desbrave.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioQrCodeService {

    private final UsuarioRepository usuarioRepository;
    private final QrCodeRepository qrCodeRepository;
    private final UsuarioQrCodeRepository usuarioQrCodeRepository;

    public UsuarioQrCodeResponse associarUsuarioQrCode(UsuarioQrCodeRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        QrCode qrCode = qrCodeRepository.findById(request.getQrCodeId())
                .orElseThrow(() -> new RuntimeException("QR Code não encontrado"));
    
        
        if (usuarioQrCodeRepository.existsByUsuarioIdAndQrCodeId(usuario.getId(), qrCode.getId())) {
            throw new RuntimeException("Este QR Code já foi escaneado por este usuário");
        }
    
       
        UsuarioQrCode usuarioQrCode = new UsuarioQrCode();
        usuarioQrCode.setUsuario(usuario);
        usuarioQrCode.setQrCode(qrCode);
        usuarioQrCode.setDataEscaneamento(LocalDateTime.now());
        
        
        Long pontos = qrCode.getPontos() != null ? qrCode.getPontos() : 50L; 
        usuarioQrCode.setPontosGanhos(pontos);
        
        
        usuario.setPontuacaoTotal(usuario.getPontuacaoTotal() + pontos);
        
        
        usuarioRepository.save(usuario);
        UsuarioQrCode saved = usuarioQrCodeRepository.save(usuarioQrCode);
        
        return toResponse(saved);
    }

    public List<UsuarioQrCodeResponse> listarAssociacoes() {
        return usuarioQrCodeRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<UsuarioQrCodeResponse> buscarAssociacoesPorUsuarioId(UUID usuarioId) {
        return usuarioQrCodeRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<UsuarioQrCodeResponse> buscarAssociacoesPorQrCodeId(UUID qrCodeId) {
        return usuarioQrCodeRepository.findByQrCodeId(qrCodeId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private UsuarioQrCodeResponse toResponse(UsuarioQrCode usuarioQrCode) {
        return new UsuarioQrCodeResponse(
                usuarioQrCode.getId(),
                toUsuarioResponse(usuarioQrCode.getUsuario()),
                toQrCodeResponse(usuarioQrCode.getQrCode()),
                usuarioQrCode.getDataEscaneamento(),
                usuarioQrCode.getPontosGanhos()  
        );
    }

    private UsuarioResponse toUsuarioResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPontuacaoTotal()
                
        );
    }

    private QrCodeResponse toQrCodeResponse(QrCode qrCode) {
        return new QrCodeResponse(
                qrCode.getId(),
                qrCode.getCodigo()
        );
    }
}