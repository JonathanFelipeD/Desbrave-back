package com.Desbrave.Desbrave.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.model.Postagem;
import com.Desbrave.Desbrave.repository.PostagemRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/postagem")
@Tag(name = "Postagem", description = "endpoints para gerenciar postagens")
public class PostagemController {

    @PostMapping
    @Operation(summary = "Cadastrar postagem")
    public Postagem criarPostagem( @RequestBody Postagem idPostagem) {
        return postagemRepository.save(idPostagem);
    }

    @GetMapping
    @Operation(summary = "Listar postagens")
    public List<Postagem> listaPostagens() {
        return postagemRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar postagem")
    public void deletarPostagem(@PathVariable("id") Long isPostagem) {
        postagemRepository.deleteById(isPostagem);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar postagem por id")
    public Postagem atualizarPostagem(@PathVariable("id") Long idPostagem, @RequestBody Postagem postagem) {
        Optional<Postagem> postagemExistente = postagemRepository.findById(idPostagem);
        if (postagemExistente.isPresent()) {
            Postagem postagemNova = postagemExistente.get();
            postagemNova.setCounteudo(postagem.getCounteudo());
            postagemNova.setDataPostagem(postagem.getDataPostagem());

            return postagemRepository.save(postagemNova);
        }
        return null;
    }

   
    private final PostagemRepository postagemRepository;
}
