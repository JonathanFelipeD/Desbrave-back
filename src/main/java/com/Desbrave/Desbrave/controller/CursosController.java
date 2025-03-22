package com.Desbrave.Desbrave.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Desbrave.Desbrave.service.CursosService;

import lombok.RequiredArgsConstructor;

import com.Desbrave.Desbrave.model.Cursos;

import java.util.List;


@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursosController {

    
    private final CursosService cursosService;

   
    @GetMapping
    public ResponseEntity<List<Cursos>> listarCursos() {
        List<Cursos> cursos = cursosService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Cursos> buscarPorId(@PathVariable int id) {
        return cursosService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<Cursos> salvarCurso(@RequestBody Cursos curso) {
        Cursos novoCurso = cursosService.salvarCurso(curso);
        return ResponseEntity.ok(novoCurso);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Cursos> atualizarCurso(@PathVariable int id, @RequestBody Cursos cursoAtualizado) {
        try {
            Cursos curso = cursosService.atualizarCurso(id, cursoAtualizado);
            return ResponseEntity.ok(curso);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable int id) {
        try {
            cursosService.deletarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
