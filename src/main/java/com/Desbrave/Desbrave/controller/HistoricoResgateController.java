package com.Desbrave.Desbrave.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.model.HistoricoResgate;
import com.Desbrave.Desbrave.repository.HistoricoResgateRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/historicoResgate")
@RequiredArgsConstructor
@Tag(name = "HistoricoResgate", description = "Historico de resgate de pontos e premios")
public class HistoricoResgateController {

    
    private final HistoricoResgateRepository historicoResgateRepository;

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar historico de resgate por usuario")
    public List<HistoricoResgate> buscarHistoricoResgatePorUsuario(@PathVariable UUID usuarioId) {
        return historicoResgateRepository.findByUsuarioId(usuarioId);
    }

}
