package com.Desbrave.Desbrave.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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
import com.Desbrave.Desbrave.model.TokenRecuperacao;
import com.Desbrave.Desbrave.repository.TokenRecuperacaoRepository;
import com.Desbrave.Desbrave.service.IMPL.EmailServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final TokenRecuperacaoRepository tokenRecuperacaoRepository;
    private final EmailServiceImpl emailServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + username));
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

    public void recuperarSenha(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com esse e-mail."));
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com esse e-mail.");
        }

        String token = UUID.randomUUID().toString();
        TokenRecuperacao tokenRecuperacao = new TokenRecuperacao();
        tokenRecuperacao.setEmail(email);
        tokenRecuperacao.setToken(token);
        tokenRecuperacao.setDataExpiracao(LocalDateTime.now().plusHours(1));
        tokenRecuperacaoRepository.save(tokenRecuperacao);

        emailServiceImpl.enviarEmail(email, "Seu token de recuperação é: " + token);
    }

    public void redefinirSenha(String token, String novaSenha) {
        TokenRecuperacao tokenRecuperacao = tokenRecuperacaoRepository.findByToken(token);
        if (tokenRecuperacao == null) {
            throw new IllegalArgumentException("Token inválido.");
        }
        if (tokenRecuperacao.getDataExpiracao().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expirado.");
        }
        Usuario usuario = usuarioRepository.findByEmail(tokenRecuperacao.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com esse e-mail."));
        usuario.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        usuarioRepository.save(usuario);
        tokenRecuperacaoRepository.delete(tokenRecuperacao);
    }

    public void enviarTokenRecuperacao(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'enviarTokenRecuperacao'");
    }
}
