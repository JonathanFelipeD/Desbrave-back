package com.Desbrave.Desbrave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.model.HistoricoResgate;
import com.Desbrave.Desbrave.repository.HistoricoResgateRepository;


@RestController
@RequestMapping("/historicoResgate")
public class HistoricoResgateController {

    @Autowired
    private HistoricoResgateRepository historicoResgateRepository;

    @GetMapping("/usuario/{usuarioId}")
    public List<HistoricoResgate> buscarHistoricoResgatePorUsuario(@PathVariable Long usuarioId) {
        return historicoResgateRepository.findByUsuarioId(usuarioId);
    }

    //aquivai ficar a lógica de buscar o historico de resgate por usuario com o id do usuario
    // e vai listar o historico de resgate
}
