package com.Desbrave.Desbrave.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.model.UsuarioCupom;
import com.Desbrave.Desbrave.service.UsuarioCupomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuariocupons")
@RequiredArgsConstructor
public class UsuarioCupomController {

    
    private final UsuarioCupomService usuarioCupomService;

    @PostMapping("/usuario/{usuarioId}")
    public UsuarioCupom adicionCupomAoUsuario(@PathVariable Long usuarioId, @RequestBody UsuarioCupom cupom){
        return usuarioCupomService.adicionarCupomAoUsuario(usuarioId, cupom);
    }
}
