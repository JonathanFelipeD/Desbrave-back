package com.Desbrave.Desbrave.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.Desbrave.Desbrave.DTO.LoginRequest;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AutenticacaoService {

    @Autowired
   private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String login (LoginRequest loginRequest){
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
        .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));
        
        if(passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())){
            return generateToken(usuario);
    } throw new RuntimeException("senha inválida");
}

    
   private String generateToken(Usuario usuario) {
       return Jwts.builder()
       .setSubject(usuario.getEmail())
       .setIssuedAt(new Date())
       .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
       .signWith(SignatureAlgorithm.HS512,"secret") // Use uma chave secreta
       .compact();
    }
    // Lógica para geracao  do token 
}
