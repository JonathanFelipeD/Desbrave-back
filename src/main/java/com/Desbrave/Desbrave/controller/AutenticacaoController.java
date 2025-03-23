package com.Desbrave.Desbrave.controller;

import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.DTO.CadastrarRequest;
import com.Desbrave.Desbrave.DTO.LoginRequest;
import com.Desbrave.Desbrave.DTO.LoginResponseDTO;
import com.Desbrave.Desbrave.config.SecurityConfig;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.security.TokenService;
import com.Desbrave.Desbrave.service.AutenticacaoService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
    
    
    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    @Operation(summary = "Autentica um usuário", description = "Autentica um usuário e retorna um token de acesso")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuário autenticado com sucesso"),
        @ApiResponse(code = 400, message = "Credenciais inválidas"),
        @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity login(@RequestBody @Validated LoginRequest loginRequest) {
      try { var UsuarioSenha = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha());
       
       var auth = authenticationManager.authenticate(UsuarioSenha);

       var token = tokenService.gerarToken((Usuario)auth.getPrincipal());

         return ResponseEntity.ok(new LoginResponseDTO(token));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
    }

    
    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastra um novo usuário", description = "Cadastra um novo usuário no sistema")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuário cadastrado com sucesso"),
        @ApiResponse(code = 400, message = "Requisição inválida"),
        @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<String> cadastrar(@RequestBody @Validated CadastrarRequest cadastrarRequest) {
        try {
            autenticacaoService.cadastrar(cadastrarRequest);
            return ResponseEntity.ok("Usuário cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar usuário");
        }
    }
}
