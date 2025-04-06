package com.Desbrave.Desbrave.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.DTO.CadastrarRequest;
import com.Desbrave.Desbrave.DTO.UsuarioCursoDTO;
import com.Desbrave.Desbrave.DTO.UsuarioUpdateDTO;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import com.Desbrave.Desbrave.service.IMPL.EmailServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "endpoints para gerenciar usuarios")
public class UsuarioController {

    
    private final UsuarioService usuarioService;
    @SuppressWarnings("unused")
    private final EmailServiceImpl emailServiceImpl;

    @GetMapping
    @Operation(summary = "Listar Usuarios")
    public ResponseEntity<List<Usuario>>listarUsuarios(){
        List<Usuario>usuarios= usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar Usuario por id")
    public ResponseEntity<Usuario>buscarUsuarioPorId(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    
        
   
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Usuario por id")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
    Usuario usuarioAtualizado = usuarioService.atualizar(id, usuarioUpdateDTO);
    if (usuarioAtualizado == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(usuarioAtualizado);
}

    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Usuario por id")
    public ResponseEntity<Usuario>deletarUsuario(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    
    @PostMapping("/recuperar-senha")
    @Operation(summary = "Solicitar recuperação de senha", description = "Solicita o envio de um email para recuperação de senha")
    public ResponseEntity<String> solicitarRecuperacaoSenha(@RequestParam String email) {
        usuarioService.solicitarRecuperacaoSenha(email);
        return ResponseEntity.ok("Email de recuperação enviado, se o endereço existir em nosso sistema");
    }

    @GetMapping("/validar-token")
    @Operation(summary = "Validar token de recuperação de senha", description = "Valida o token enviado para o email do usuário")
    public ResponseEntity<String> validarToken(@RequestParam String token) {
        
        boolean tokenValido = usuarioService.validarTokenRecuperacao(token);
        return tokenValido ? ResponseEntity.ok("Token válido") : ResponseEntity.badRequest().body("Token inválido ou expirado");
    }

    @PostMapping("/redefinir-senha")
    @Operation(summary = "Redefinir senha", description = "Redefine a senha do usuário usando o token enviado por email")
    public ResponseEntity<String> redefinirSenha(
            @RequestParam String token,
            @RequestParam String novaSenha) {
        
        
        boolean sucesso = usuarioService.redefinirSenha(token, novaSenha);
        return sucesso ? ResponseEntity.ok("Senha redefinida com sucesso") : 
                         ResponseEntity.badRequest().body("Falha ao redefinir senha. Token inválido ou expirado");
    }


    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastra um novo usuário", description = "Cadastra um novo usuário no sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<String> cadastrar(@RequestBody @Validated CadastrarRequest cadastrarRequest) {
        try {
            usuarioService.cadastrar(cadastrarRequest);
            return ResponseEntity.ok("Usuário cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar usuário");
        }
    }
    @GetMapping("/{id}/cursos-com-progresso")
@Operation(summary = "Listar cursos iniciados por um usuário com progresso e data de início")
public ResponseEntity<List<UsuarioCursoDTO>> listarCursosComProgresso(@PathVariable Long id) {
    List<UsuarioCursoDTO> cursosComProgresso = usuarioService.listarCursosIniciadosPorUsuario(id);

    return ResponseEntity.ok(cursosComProgresso);
}

    }
