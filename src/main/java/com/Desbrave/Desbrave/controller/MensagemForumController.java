package com.Desbrave.Desbrave.controller;

import com.Desbrave.Desbrave.DTO.MensagemForumRequest;
import com.Desbrave.Desbrave.DTO.MensagemForumResponse;
import com.Desbrave.Desbrave.service.MensagemForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/forum/chat")

public class MensagemForumController {



        private final MensagemForumService mensagemService;


        public MensagemForumController(MensagemForumService mensagemService) {
            this.mensagemService = mensagemService;
        }


        @PostMapping("/chat")
        public ResponseEntity<MensagemForumResponse> enviar(@RequestBody MensagemForumRequest request) {
            MensagemForumResponse response = mensagemService.enviarMensagem(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }


        @GetMapping("/chat/{tituloForum}")
        public ResponseEntity<List<MensagemForumResponse>> listarMensagens(@PathVariable String tituloForum) {
            List<MensagemForumResponse> mensagens = mensagemService.listarMensagensPorForum(tituloForum);
            return ResponseEntity.ok(mensagens);
        }


        @PutMapping("/chat/{id}")
        public ResponseEntity<MensagemForumResponse> atualizarMensagem(
                @PathVariable UUID id,
                @RequestBody MensagemForumRequest request) {

            MensagemForumResponse atualizada = mensagemService.atualizarMensagem(id, request);
            return ResponseEntity.ok(atualizada);
        }


        @DeleteMapping("/chat/{id}")
        public ResponseEntity<Void> deletarMensagem(@PathVariable UUID id) {
            mensagemService.deletarMensagem(id);
            return ResponseEntity.noContent().build();
        }
    }
