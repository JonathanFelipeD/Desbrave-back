package com.Desbrave.Desbrave.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.model.Cupom;
import com.Desbrave.Desbrave.service.CupomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/cupom")
@RequiredArgsConstructor
@Tag(name = "Cupom", description = "Endpoints para gerenciamento de cupons")
public class CupomController {

    private final CupomService cupomService;

    @PostMapping
    @Operation(summary = "Cadastrar um cupom", description = "Cadastra um novo cupom")
    public Cupom cadastrarCupom(@RequestBody Cupom cupom) {
        return cupomService.criarCupom(cupom);
    }

    @GetMapping
    @Operation(summary = "Listar cupons", description = "Lista todos os cupons cadastrados")
    public List<Cupom> listaCupons() {
        return cupomService.listarCupons();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um cupom", description = "Deleta um cupom pelo id")
    @ApiResponse(responseCode = "200", description = "Cupom deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Cupom não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public void deletarCupom(@PathVariable("id") UUID idCupom) {
        cupomService.deletarCupom(idCupom);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um cupom", description = "Atualiza um cupom pelo id")
    @ApiResponse(responseCode = "200", description = "Cupom atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Cupom não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public Cupom atualizarCupomPeloId(@PathVariable("id") UUID idCupom, @RequestBody Cupom cupom) {
        return cupomService.atualizarCupom(idCupom, cupom);
    }
}