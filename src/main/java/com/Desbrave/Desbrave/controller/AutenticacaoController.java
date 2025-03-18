package com.Desbrave.Desbrave.controller;

import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.DTO.CadastrarRequest;
import com.Desbrave.Desbrave.DTO.LoginRequest;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginRequest loginRequest) {
       var UsuarioSenha = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha());
       @SuppressWarnings("unused")
       var authentication = authenticationManager.authenticate(UsuarioSenha);
         return ResponseEntity.ok().build();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Validated CadastrarRequest cadastrarRequest) {
        if(this.usuarioRepository.findByEmail(cadastrarRequest.getEmail()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(cadastrarRequest.getSenha());
        Usuario novoUsuario = new Usuario(cadastrarRequest.getEmail(), encryptedPassword, cadastrarRequest.getTipoUsuario());
        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
    
}
