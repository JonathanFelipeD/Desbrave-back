package com.Desbrave.Desbrave.controller;

import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.DTO.CadastrarRequest;
import com.Desbrave.Desbrave.DTO.LoginRequest;
import com.Desbrave.Desbrave.DTO.LoginResponseDTO;
import com.Desbrave.Desbrave.constants.TipoUsuario;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.UsuarioRepository;
import com.Desbrave.Desbrave.security.TokenService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


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
@RequiredArgsConstructor
public class AutenticacaoController {

    
    private final UsuarioRepository usuarioRepository;

    
    private final AuthenticationManager authenticationManager;

    
    private final TokenService tokenService;
    
    
    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginRequest loginRequest) {
      try { var UsuarioSenha = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha());
       
       var auth = authenticationManager.authenticate(UsuarioSenha);

       var token = tokenService.gerarToken((Usuario)auth.getPrincipal());

         return ResponseEntity.ok(new LoginResponseDTO(token));


    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
    }

    
    @SuppressWarnings("rawtypes")
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Validated CadastrarRequest cadastrarRequest) {
        if(this.usuarioRepository.findByEmail(cadastrarRequest.getEmail()) != null){
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(cadastrarRequest.getSenha());


        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(cadastrarRequest.getEmail());
        novoUsuario.setSenha(encryptedPassword);
        novoUsuario.setTipoUsuario(TipoUsuario.valueOf(cadastrarRequest.getTipoUsuario().toUpperCase()));
        novoUsuario.setDataNascimento(cadastrarRequest.getDataNascimento());
        novoUsuario.setDataCriacao(LocalDate.now()); 
        novoUsuario.setPontuacaoTotal(0);

        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
