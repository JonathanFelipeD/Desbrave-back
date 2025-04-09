package com.Desbrave.Desbrave.service;

import com.Desbrave.Desbrave.DTO.MensagemForumRequest;
import com.Desbrave.Desbrave.DTO.MensagemForumResponse;
import com.Desbrave.Desbrave.model.Forum;
import com.Desbrave.Desbrave.model.MensagemForum;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.ForumRepository;
import com.Desbrave.Desbrave.repository.MensagemForumRepository;
import com.Desbrave.Desbrave.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class MensagemForumService {

    private final MensagemForumRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final ForumRepository forumRepository;

    public List<Forum> listarTodosForunsComUsuarios() {
        return forumRepository.findAllWithUsuario(); // Usando o método corrigido
    }
    public MensagemForumService(
            MensagemForumRepository mensagemRepository,
            UsuarioRepository usuarioRepository,
            ForumRepository forumRepository
    ) {
        this.mensagemRepository = mensagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.forumRepository = forumRepository;
    }


    public MensagemForumResponse enviarMensagem(MensagemForumRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Forum forum = forumRepository.findByTitulo(request.getTituloForum().name())
                .orElseThrow(() -> new RuntimeException("Fórum não encontrado"));

        MensagemForum mensagem = new MensagemForum();
        mensagem.setUsuario(usuario);
        mensagem.setForum(forum);
        mensagem.setConteudo(request.getConteudo());

        MensagemForum salva = mensagemRepository.save(mensagem);
        return toResponse(salva);
    }


    public void deletarMensagem(UUID id) {
        MensagemForum mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));
        mensagemRepository.delete(mensagem);
    }


    public MensagemForumResponse atualizarMensagem(UUID id, MensagemForumRequest request) {
        MensagemForum mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));

        mensagem.setConteudo(request.getConteudo());
        mensagem.setDataEnvio(LocalDateTime.now());

        return toResponse(mensagemRepository.save(mensagem));
    }


    public List<MensagemForumResponse> listarMensagensPorForum(String tituloForum) {
        Forum forum = forumRepository.findByTitulo(tituloForum)
                .orElseThrow(() -> new RuntimeException("Fórum não encontrado"));

        List<MensagemForum> mensagens = mensagemRepository.findByForumIdOrderByDataEnvioAsc(forum.getId());

        return mensagens.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


    private MensagemForumResponse toResponse(MensagemForum mensagem) {
        MensagemForumResponse response = new MensagemForumResponse();
        response.setId(mensagem.getId());
        response.setConteudo(mensagem.getConteudo());
        response.setDataEnvio(mensagem.getDataEnvio());

        if (mensagem.getUsuario() != null) {
            response.setNomeUsuario(mensagem.getUsuario().getNome());
        }

        if (mensagem.getForum() != null) {
            response.setTituloForum(mensagem.getForum().getTitulo());
        }

        return response;
    }

}
