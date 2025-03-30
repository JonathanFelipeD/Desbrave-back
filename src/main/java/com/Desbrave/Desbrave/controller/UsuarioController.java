package com.Desbrave.Desbrave.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "endpoints para gerenciar usuarios")
public class UsuarioController {

    
    private final UsuarioService usuarioService;

    //metodo pra listar os usuarios
    @GetMapping
    @Operation(summary = "Listar Usuarios")
    public ResponseEntity<List<Usuario>>listarUsuarios(){
        List<Usuario>usuarios= usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    //buscar por id especifico
    @GetMapping("/{id}")
    @Operation(summary = "Buscar Usuario por id")
    public ResponseEntity<Usuario>buscarUsuarioPorId(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    //criar novo usuario
    @PostMapping
    @Operation(summary = "Cadastrar Usuario")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.criar(usuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }
        
    //att um usuario com o id
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Usuario por id")
    public ResponseEntity<Usuario>atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioAtualizado = usuarioService.atualizar(id , usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    //del usuario
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Usuario por id")
    public ResponseEntity<Usuario>deletarUsuario(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    }
