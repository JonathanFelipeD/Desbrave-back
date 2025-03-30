package com.Desbrave.Desbrave.service;

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

    //a logica vai adicionar um cupom a um usuario
    public UsuarioCupom adicionarCupomAoUsuario(Long usuarioId, UsuarioCupom cupom){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        cupom.setUsuario(usuario);

        return usuarioCupomRepository.save(cupom);
    }

}
