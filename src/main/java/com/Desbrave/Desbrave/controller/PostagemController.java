package com.Desbrave.Desbrave.controller;

import com.Desbrave.Desbrave.DTO.PostagemRequest;
import com.Desbrave.Desbrave.DTO.PostagemResponse;
import com.Desbrave.Desbrave.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/postagens")
@Tag(name = "Postagem", description = "Endpoints para gerenciar postagens")
public class PostagemController {
    private final PostagemService postagemService;

    @PostMapping
    @Operation(summary = "Criar nova postagem")
    @ResponseStatus(HttpStatus.CREATED)
    public PostagemResponse criarPostagem(@RequestBody PostagemRequest request) {
        return postagemService.criarPostagem(request);
    }

    @GetMapping
    @Operation(summary = "Listar todas as postagens")
    public List<PostagemResponse> listarPostagens() {
        return postagemService.listarPostagens();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar postagem por ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPostagem(@PathVariable UUID id) {
        postagemService.deletarPostagem(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar postagem por ID")
    public PostagemResponse atualizarPostagem(
            @PathVariable UUID id, 
            @RequestBody PostagemRequest request) {
        return postagemService.atualizarPostagem(id, request);
    }
}