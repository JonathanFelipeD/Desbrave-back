package com.Desbrave.Desbrave.controller;

import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.DTO.CadastrarRequest;
import com.Desbrave.Desbrave.DTO.LoginRequest;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginRequest loginRequest) {
      try { var UsuarioSenha = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha());
       @SuppressWarnings("unused")
       var auth = authenticationManager.authenticate(UsuarioSenha);
         return ResponseEntity.ok().build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
    }

    
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Validated CadastrarRequest cadastrarRequest) {
        if(this.usuarioRepository.findByEmail(cadastrarRequest.getEmail()) != null){
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(cadastrarRequest.getSenha());
        Usuario novoUsuario = new Usuario(cadastrarRequest.getEmail(), encryptedPassword, cadastrarRequest.getTipoUsuario());
        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }
    
}
