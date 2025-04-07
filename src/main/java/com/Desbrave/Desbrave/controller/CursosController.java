package com.Desbrave.Desbrave.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Desbrave.Desbrave.service.CursosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import com.Desbrave.Desbrave.model.Cursos;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
@Tag(name = "Cursos", description = "Endpoints para gerenciamento de cursos")
public class CursosController {

    
    private final CursosService cursosService;

   
    @GetMapping
    @Operation(summary = "Listar cursos", description = "Lista todos os cursos cadastrados")
    public ResponseEntity<List<Cursos>> listarCursos() {
        List<Cursos> cursos = cursosService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar curso por id", description = "Busca um curso pelo id")
    public ResponseEntity<Cursos> buscarPorId(@PathVariable UUID id) {
        return cursosService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    @Operation(summary = "Cadastrar um curso", description = "Cadastra um novo curso")
    public ResponseEntity<Cursos> salvarCurso(@RequestBody Cursos curso) {
        Cursos novoCurso = cursosService.salvarCurso(curso);
        return ResponseEntity.ok(novoCurso);
    }

    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um curso", description = "Atualiza um curso pelo id")
    public ResponseEntity<Cursos> atualizarCurso(@PathVariable UUID id, @RequestBody Cursos cursoAtualizado) {
        try {
            Cursos curso = cursosService.atualizarCurso(id, cursoAtualizado);
            return ResponseEntity.ok(curso);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um curso", description = "Deleta um curso pelo id")
    public ResponseEntity<Void> deletarCurso(@PathVariable UUID id) {
        try {
            cursosService.deletarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
