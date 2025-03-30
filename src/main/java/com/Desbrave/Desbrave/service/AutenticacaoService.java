package com.Desbrave.Desbrave.service;


import java.time.LocalDate;

import org.springframework.security.core.userdetails.User;
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
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getTipoUsuario().name())
                .build();
    }

    public void cadastrar(CadastrarRequest cadastrarRequest) {

        if (usuarioRepository.findByEmail(cadastrarRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(cadastrarRequest.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(cadastrarRequest.getSenha()));
        novoUsuario.setTipoUsuario(TipoUsuario.valueOf(cadastrarRequest.getTipoUsuario().toUpperCase()));
        novoUsuario.setDataNascimento(cadastrarRequest.getDataNascimento());
        novoUsuario.setDataCriacao(LocalDate.now());
        novoUsuario.setPontuacaoTotal(0);

        usuarioRepository.save(novoUsuario);
    }
}