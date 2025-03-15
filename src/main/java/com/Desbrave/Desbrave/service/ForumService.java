package com.Desbrave.Desbrave.service;

import com.Desbrave.Desbrave.model.Forum;
import com.Desbrave.Desbrave.repository.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumService {

    @Autowired
    private ForumRepository forumRepository;

    public List<Forum> listarTodos() {
        return forumRepository.findAll();
    }

    public Optional<Forum> buscarPorId(Integer id) {
        return forumRepository.findById(id);
    }

    public Forum salvar(Forum forum) {
        return forumRepository.save(forum);
    }

    public Forum atualizar(Integer id, Forum forumAtualizado) {
        return forumRepository.findById(id).map(forum -> {
            forum.setTitulo(forumAtualizado.getTitulo());
            forum.setDescricao(forumAtualizado.getDescricao());
            forum.setDataCriacao(forumAtualizado.getDataCriacao());
            forum.setUsuarioId(forumAtualizado.getUsuarioId());
            return forumRepository.save(forum);
        }).orElseThrow(() -> new RuntimeException("Fórum não encontrado"));
    }

    public void deletar(Integer id) {
        if (forumRepository.existsById(id)) {
            forumRepository.deleteById(id);
        } else {
            throw new RuntimeException("Fórum não encontrado");
        }
    }
}
