package com.Desbrave.Desbrave.service;


import java.time.LocalDate;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Desbrave.Desbrave.DTO.CadastrarRequest;
import com.Desbrave.Desbrave.constants.TipoUsuario;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }

    
    public void cadastrar(CadastrarRequest cadastrarRequest) {
        if (usuarioRepository.findByEmail(cadastrarRequest.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(cadastrarRequest.getSenha());

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(cadastrarRequest.getEmail());
        novoUsuario.setSenha(encryptedPassword);
        novoUsuario.setTipoUsuario(TipoUsuario.valueOf(cadastrarRequest.getTipoUsuario().toUpperCase()));
        novoUsuario.setDataNascimento(cadastrarRequest.getDataNascimento());
        novoUsuario.setDataCriacao(LocalDate.now());
        novoUsuario.setPontuacaoTotal(0);

        usuarioRepository.save(novoUsuario);
    }
}
