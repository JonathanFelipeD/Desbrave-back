package com.Desbrave.Desbrave.service;

import com.Desbrave.Desbrave.DTO.CadastrarRequest;
import com.Desbrave.Desbrave.DTO.UsuarioCursoDTO;
import com.Desbrave.Desbrave.constants.TipoUsuario;
import com.Desbrave.Desbrave.model.TokenRecuperacao;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.TokenRecuperacaoRepository;
import com.Desbrave.Desbrave.repository.UsuarioRepository;
import com.Desbrave.Desbrave.service.IMPL.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmailServiceImpl emailServiceImpl;
    private final TokenRecuperacaoRepository tokenRecuperacaoRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public Usuario criar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        return null;
    }
    
    public Boolean deletar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
         public void cadastrar(CadastrarRequest cadastrarRequest) {
    
            if (usuarioRepository.findByEmail(cadastrarRequest.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email já cadastrado");
            }
    
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(cadastrarRequest.getNome());
            novoUsuario.setEmail(cadastrarRequest.getEmail());
            novoUsuario.setSenha(passwordEncoder.encode(cadastrarRequest.getSenha()));
            novoUsuario.setTipoUsuario(TipoUsuario.valueOf(cadastrarRequest.getTipoUsuario().toUpperCase()));
            novoUsuario.setDataCriacao(LocalDate.now());
            novoUsuario.setPontuacaoTotal(0);
    
            usuarioRepository.save(novoUsuario);
        }
    
    @Transactional
    public void criarTokenRecuperacao(String email) {
        
        tokenRecuperacaoRepository.deleteByEmail(email);
        
        
        TokenRecuperacao novoToken = new TokenRecuperacao();
        novoToken.setEmail(email);
        novoToken.setToken(UUID.randomUUID().toString());
        novoToken.setDataExpiracao(LocalDateTime.now().plusHours(1));
        tokenRecuperacaoRepository.save(novoToken);
    }

    public boolean validarTokenRecuperacao(String token) {
        TokenRecuperacao tokenRecuperacao = tokenRecuperacaoRepository.findByToken(token);
        return tokenRecuperacao != null && 
               tokenRecuperacao.getDataExpiracao().isAfter(LocalDateTime.now());
    }
    
    @Transactional
    public boolean redefinirSenha(String token, String novaSenha) {
        TokenRecuperacao tokenRecuperacao = tokenRecuperacaoRepository.findByToken(token);
        if (tokenRecuperacao == null || tokenRecuperacao.getDataExpiracao().isBefore(LocalDateTime.now())) {
            return false;
        }
        
        Usuario usuario = usuarioRepository.findByEmail(tokenRecuperacao.getEmail()).orElse(null);
        if (usuario != null) {
            usuario.setSenha(passwordEncoder.encode(novaSenha));
            usuarioRepository.save(usuario);
            tokenRecuperacaoRepository.delete(tokenRecuperacao);
            return true;
        }
        return false;
    }

    @Transactional
    public void solicitarRecuperacaoSenha(String email) {
        Usuario usuario = buscarPorEmail(email);
        if (usuario != null) {
            criarTokenRecuperacao(email);
            TokenRecuperacao token = tokenRecuperacaoRepository.findByEmail(email);
            
            String linkRecuperacao = "http://localhost:8080/auth/recuperar-senha?token=" + token.getToken();
            emailServiceImpl.enviarEmail(
                usuario.getEmail(), 
                "Clique no link para redefinir sua senha: " + linkRecuperacao
            );
        }
    }

    public List<UsuarioCursoDTO> listarCursosIniciadosPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        return usuario.getCursosIniciados().stream()
                .map(cursoUsuario -> new UsuarioCursoDTO(
                        cursoUsuario.getCurso().getTitulo(),
                        cursoUsuario.getProgresso(),
                        cursoUsuario.getDataInicio()
                ))
                .collect(Collectors.toList());
    }

    // Novo método: Conta a quantidade de cursos iniciados pelo usuário
    public int contarCursosIniciados(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuario.getCursosIniciados().size();
    }
    // ✅ NOVO MÉTODO ADICIONADO - retorna lista com progresso dos cursos iniciados pelo usuário
public List<UsuarioCursoDTO> listarCursosComProgresso(Long usuarioId) {
    Usuario usuario = buscarPorId(usuarioId);
    if (usuario == null || usuario.getCursosIniciados() == null) {
        return List.of(); // Retorna lista vazia se o usuário ou os cursos forem nulos
    }

    return usuario.getCursosIniciados().stream()
            .map(curso -> new UsuarioCursoDTO(
                    curso.getCurso().getTitulo(),
                    curso.getProgresso(),
                    curso.getDataInicio()
            ))
            .collect(Collectors.toList());
}


}




