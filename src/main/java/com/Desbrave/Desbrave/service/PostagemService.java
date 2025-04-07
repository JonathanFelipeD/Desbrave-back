package com.Desbrave.Desbrave.service;

import com.Desbrave.Desbrave.DTO.PostagemRequest;
import com.Desbrave.Desbrave.DTO.PostagemResponse;
import com.Desbrave.Desbrave.model.*;
import com.Desbrave.Desbrave.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostagemService {
    private final PostagemRepository postagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final ForumRepository forumRepository;

    public PostagemResponse criarPostagem(PostagemRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        
        Forum forum = null;
        if (request.getForumId() != null) {
            forum = forumRepository.findById(request.getForumId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fórum não encontrado"));
        }

        Postagem postagem = new Postagem();
        postagem.setConteudo(request.getConteudo());
        // postagem.setUsuario(usuario);
        // postagem.setForum(forum);

        Postagem saved = postagemRepository.save(postagem);
        return toResponse(saved);
    }

    public List<PostagemResponse> listarPostagens() {
        return postagemRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void deletarPostagem(UUID id) {
        if (!postagemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada");
        }
        postagemRepository.deleteById(id);
    }

    public PostagemResponse atualizarPostagem(UUID id, PostagemRequest request) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada"));

        postagem.setConteudo(request.getConteudo());
        
        if (request.getForumId() != null) {
            Forum forum = forumRepository.findById(request.getForumId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fórum não encontrado"));
            postagem.setForum(forum);
        }

        Postagem updated = postagemRepository.save(postagem);
        return toResponse(updated);
    }

    private PostagemResponse toResponse(Postagem postagem) {
        PostagemResponse response = new PostagemResponse();
        response.setId(postagem.getId());
        response.setConteudo(postagem.getConteudo());
        response.setDataPostagem(postagem.getDataPostagem());
        response.setNomeUsuario(postagem.getUsuario().getNome());
        
        if (postagem.getForum() != null) {
            response.setTituloForum(postagem.getForum().getTitulo());
        }
        
        return response;
    }
}