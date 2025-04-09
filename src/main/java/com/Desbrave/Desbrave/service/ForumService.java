package com.Desbrave.Desbrave.service;

import com.Desbrave.Desbrave.DTO.ForumRequest;
import com.Desbrave.Desbrave.model.Forum;
import com.Desbrave.Desbrave.repository.ForumRepository;
import com.Desbrave.Desbrave.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ForumService {
    private final UsuarioRepository usuarioRepository;
    private final ForumRepository forumRepository;


    public List<Forum> listarTodos() {
        return forumRepository.findAll(); // Método padrão do JpaRepository
    }

    public List<Forum> listarPorUsuario(UUID usuarioId) {
        return forumRepository.findByUsuario_Id(usuarioId);
    }


    @Transactional
    public Forum criarForum(ForumRequest request) {
        Forum forum = new Forum();
        forum.setTitulo(request.getTitulo());
        forum.setDescricao(request.getDescricao());
        return forumRepository.save(forum);
    }

    public Forum salvar(Forum forum) {
        return forumRepository.save(forum);
    }

    @Transactional
    public Forum atualizar(UUID id, Forum forumAtualizado) {
        return forumRepository.findById(id).map(forum -> {
            forum.setTitulo(forumAtualizado.getTitulo());
            forum.setDescricao(forumAtualizado.getDescricao());
            return forumRepository.save(forum);
        }).orElseThrow(() -> new RuntimeException("Fórum não encontrado"));
    }
}
