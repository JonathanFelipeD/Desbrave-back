package com.Desbrave.Desbrave.service;

import com.Desbrave.Desbrave.DTO.ForumRequest;
import com.Desbrave.Desbrave.model.Forum;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.ForumRepository;
import com.Desbrave.Desbrave.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {

    private final UsuarioRepository usuarioRepository;
    private final ForumRepository forumRepository;

    public List<Forum> listarTodos() {
        return forumRepository.findAll();
    }

    public Forum buscarPorId(Integer id) {
        return forumRepository.findById(id)
                             .orElse(null); 
    }

    
    @Transactional
    public Forum criarForum(ForumRequest request) {
       
        
        Forum forum = new Forum();
        forum.setTitulo(request.getTitulo()); 
        forum.setDescricao(request.getDescricao());
        
        
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        forum.setUsuario(usuario); 
        
        return forumRepository.save(forum);
    }

    public Forum salvar(Forum forum) {
        return forumRepository.save(forum);
    }

    @Transactional
    public Forum atualizar(Long id, Forum forumAtualizado) {
        return forumRepository.findById(id).map(forum -> {
            forum.setTitulo(forumAtualizado.getTitulo());
            forum.setDescricao(forumAtualizado.getDescricao());
            if (forumAtualizado.getUsuario() != null) {
                Usuario usuario = usuarioRepository.findById(forumAtualizado.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
                forum.setUsuario(usuario);
            }
            
            return forumRepository.save(forum);
        }).orElseThrow(() -> new RuntimeException("Fórum não encontrado"));
    }
}
