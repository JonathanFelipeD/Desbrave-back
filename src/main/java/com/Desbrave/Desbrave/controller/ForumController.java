package com.Desbrave.Desbrave.controller;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.Desbrave.Desbrave.model.Forum;
import com.Desbrave.Desbrave.service.ForumService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/forum")
@RequiredArgsConstructor
@Tag(name = "Forum", description = "Endpoints para gerenciar Forum")
public class ForumController {

    private final ForumService forumService;

    @GetMapping
    @Operation(summary = "Listar Forum", description = "Lista todos os Forum cadastrados")
    public List<Forum> getAllForums() {
        return forumService.listarTodos();
    }

    @PostMapping
    @Operation(summary = "Criar Forum", description = "Cria um novo Forum")
    public Forum createForum(@RequestBody Forum forum) {
        return forumService.salvar(forum);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Forum por id", description = "Busca um Forum pelo id")
    public Forum getForumById(@PathVariable Integer id) {
        return forumService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Forum Não Encontrado"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Forum", description = "Deleta um Forum pelo id")
    public void deleteForum(@PathVariable Integer id) {
        forumService.deletar(id);
    }
}
