package com.Desbrave.Desbrave.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.Desbrave.Desbrave.DTO.ForumRequest;
import com.Desbrave.Desbrave.DTO.ForumResponse;
import com.Desbrave.Desbrave.DTO.ForumView;
import com.Desbrave.Desbrave.model.Forum;
import com.Desbrave.Desbrave.repository.ForumRepository;
import com.Desbrave.Desbrave.service.ForumService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/forum")
@RequiredArgsConstructor
@Tag(name = "Forum", description = "endpoints para gerenciar Forum")
public class ForumController {


    private final ForumService forumService;
    private final ForumRepository forumRepository;

    
    @Operation(summary = "Listar Forum", description = "Lista todos os Forum cadastrados")
    @GetMapping
    public List<Forum> listarTodos() {
        return forumService.listarTodos();
    }

    @Operation(summary = "Criar Forum", description = "Cria um novo Forum")
    @PostMapping
    public ResponseEntity<?> criarForum(@Valid @RequestBody ForumRequest request) {
    try {
        Forum forumCriado = forumService.criarForum(request);
        return ResponseEntity.status(201).body(forumCriado);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
@GetMapping("/{id}")
@Operation(summary = "Buscar Forum por id")
public ResponseEntity<ForumResponse> getForumById(@PathVariable UUID id) {
    Forum forum = forumRepository.findById(id)
                               .orElseThrow(() -> new RuntimeException("Forum Não Encontrado"));
    
    return ResponseEntity.ok(new ForumResponse(forum));
}

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Forum", description = "Deleta um Forum pelo id")
        public void deletarForum(@PathVariable UUID id){
            forumRepository.deleteById(id);   
        } 
    





}
