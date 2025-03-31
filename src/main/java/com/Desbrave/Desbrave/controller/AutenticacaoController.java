package com.Desbrave.Desbrave.controller;

import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.DTO.LoginRequest;
import com.Desbrave.Desbrave.DTO.LoginResponseDTO;
import com.Desbrave.Desbrave.config.SecurityConfig;
import com.Desbrave.Desbrave.model.Usuario;
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

   

    
    @SuppressWarnings({"rawtypes", "UseSpecificCatch"})
    @PostMapping("/login")
    @Operation(summary = "Autentica um usuário", description = "Autentica um usuário e retorna um token de acesso")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
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

    
    @PostMapping("/logout")
    @Operation(summary = "Desloga um usuário", description = "Invalida o token JWT e desloga o usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Logout realizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Token inválido ou expirado"), 
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<String> logout(@RequestBody String token) {
        try {
            autenticacaoService.invalidarToken(token);
            return ResponseEntity.ok("Logout realizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar logout");
        }
    }
    

}

