package com.Desbrave.Desbrave.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.model.UsuarioCupom;
import com.Desbrave.Desbrave.repository.UsuarioCupomRepository;
import com.Desbrave.Desbrave.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioCupomService {

   
    private final UsuarioCupomRepository usuarioCupomRepository;
private final UsuarioRepository usuarioRepository;

   
    public UsuarioCupom adicionarCupomAoUsuario(UUID usuarioId, UsuarioCupom cupom){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        cupom.setUsuario(usuario);

        return usuarioCupomRepository.save(cupom);
    }

}
