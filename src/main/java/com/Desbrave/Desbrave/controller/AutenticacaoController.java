package com.Desbrave.Desbrave.controller;

import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.DTO.LoginRequest;
import com.Desbrave.Desbrave.DTO.LoginResponseDTO;
import com.Desbrave.Desbrave.config.SecurityConfig;
import com.Desbrave.Desbrave.security.TokenService;
import com.Desbrave.Desbrave.service.AutenticacaoService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
@Tag(name = "Autenticação" , description = "Endpoints para autenticação de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository; // Adicionado para buscar ID

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginRequest loginRequest) {
        try {

            Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));


            if (!new BCryptPasswordEncoder().matches(loginRequest.getSenha(), usuario.getSenha())) {
                throw new BadCredentialsException("Senha incorreta");
            }


            String token = tokenService.gerarToken(usuario.getId().toString());

            return ResponseEntity.ok(new LoginResponseDTO(token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token inválido ou não fornecido");
        }
        try {
            autenticacaoService.invalidarToken(token);
            return ResponseEntity.ok("Logout realizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar logout");
        }
    }
}

