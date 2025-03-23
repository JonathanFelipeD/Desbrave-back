package com.Desbrave.Desbrave.service;


import org.springframework.stereotype.Service;
import com.Desbrave.Desbrave.repository.CursosRepository;

import lombok.RequiredArgsConstructor;

import com.Desbrave.Desbrave.model.Cursos;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursosService {

    
    private final CursosRepository cursosRepository;

    public List<Cursos> listarCursos() {
        return cursosRepository.findAll();
    }

 
    public Optional<Cursos> buscarPorId(int id) {
        return cursosRepository.findById(id);
    }

    
    public Cursos salvarCurso(Cursos curso) {
        return cursosRepository.save(curso);
    }

    public Cursos atualizarCurso(int id, Cursos cursoAtualizado) {
        return cursosRepository.findById(id)
                .map(curso -> {
                    curso.setTitulo(cursoAtualizado.getTitulo());
                    curso.setDescricao(cursoAtualizado.getDescricao());
                    curso.setCategoria(cursoAtualizado.getCategoria());
                    curso.setCargaHoraria(cursoAtualizado.getCargaHoraria());
                    curso.setStatus(cursoAtualizado.getStatus());
                    curso.setUrlExterna(cursoAtualizado.getUrlExterna());
                    return cursosRepository.save(curso);
                }).orElseThrow(() -> new RuntimeException("Curso com ID " + id + " não encontrado."));
    }

    public void deletarCurso(int id) {
        if (cursosRepository.existsById(id)) {
            cursosRepository.deleteById(id);
        } else {
            throw new RuntimeException("Curso com ID " + id + " não encontrado.");
        }
    }
}