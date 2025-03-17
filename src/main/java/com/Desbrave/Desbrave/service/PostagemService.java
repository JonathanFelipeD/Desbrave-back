package com.Desbrave.Desbrave.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.Desbrave.Desbrave.model.Postagem;
import com.Desbrave.Desbrave.repository.PostagemRepository;

@Service
public class PostagemService {

    private final PostagemRepository postagemRepository;

    public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    public Postagem criarPostagem(Postagem postagem) {
        Postagem postagemCriada = postagemRepository.save(postagem);
        System.out.println("Postagem Criada com Sucesso!");
        return postagemCriada;
    }

    public List<Postagem> listaPostagens() {
        return postagemRepository.findAll();
    }

    public void deletarPostagem(@PathVariable("id") Long isPostagem) {
        postagemRepository.deleteById(isPostagem);
        System.out.println("Postagem Deletada com Sucesso!");
    }

    public Postagem atualizarPostagem(@PathVariable("id") Long idPostagem, @RequestBody Postagem postagem) {
        Optional<Postagem> postagemExistente = postagemRepository.findById(idPostagem);
        if (postagemExistente.isPresent()) {
            Postagem postagemNova = postagemExistente.get();
            postagemNova.setCounteudo(postagem.getCounteudo());
            postagemNova.setDataPostagem(postagem.getDataPostagem());

            Postagem postagemNovaAtualizada = postagemRepository.save(postagemNova);
            System.out.println("Postagem Atualizada Com Sucesso!");

            return postagemNovaAtualizada;
        }
        return postagem;
    }
}
