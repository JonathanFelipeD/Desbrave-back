package com.Desbrave.Desbrave.service;

import com.Desbrave.Desbrave.DTO.CadastrarRequest;
import com.Desbrave.Desbrave.DTO.UsuarioCompletoResponse;
import com.Desbrave.Desbrave.DTO.UsuarioCursoDTO;
import com.Desbrave.Desbrave.DTO.UsuarioQrCodeResponse;
import com.Desbrave.Desbrave.DTO.UsuarioUpdateDTO;
import com.Desbrave.Desbrave.constants.TipoUsuario;
import com.Desbrave.Desbrave.model.CursoUsuario;
import com.Desbrave.Desbrave.model.TokenRecuperacao;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.CursoUsuarioRepository;
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

    private final UsuarioQrCodeService usuarioQrCodeService;
    private final UsuarioRepository usuarioRepository;
    private final EmailServiceImpl emailServiceImpl;
    private final TokenRecuperacaoRepository tokenRecuperacaoRepository;
    private final PasswordEncoder passwordEncoder;
    private final CursoUsuarioRepository cursoUsuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

   

   public Usuario atualizar(UUID id, UsuarioUpdateDTO usuarioUpdateDTO) {
    if (usuarioRepository.existsById(id)) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        
        if (usuarioUpdateDTO.getNome() != null) {
            usuarioExistente.setNome(usuarioUpdateDTO.getNome());
        }
        if (usuarioUpdateDTO.getEmail() != null) {
            usuarioExistente.setEmail(usuarioUpdateDTO.getEmail());
        }
        if (usuarioUpdateDTO.getSenha() != null) {
            usuarioExistente.setSenha(usuarioUpdateDTO.getSenha());
        }
        if (usuarioUpdateDTO.getDataNascimento() != null) {
            usuarioExistente.setDataNascimento(usuarioUpdateDTO.getDataNascimento());
        }

        return usuarioRepository.save(usuarioExistente);
    }
    return null;
}
    
    public Boolean deletar(UUID id) {
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

    public List<UsuarioCursoDTO> listarCursosIniciadosPorUsuario(UUID usuarioId) {
    List<CursoUsuario> cursosIniciados = cursoUsuarioRepository.findByUsuarioId(usuarioId);

    return cursosIniciados.stream()
            .map(cursoUsuario -> new UsuarioCursoDTO(
                    cursoUsuario.getCurso().getTitulo(),
                    cursoUsuario.getProgresso(),
                    cursoUsuario.getDataInicio()
            ))
            .collect(Collectors.toList());
}

public UsuarioCompletoResponse buscarUsuarioCompleto(UUID id) {
    Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
    List<UsuarioQrCodeResponse> qrCodes = usuarioQrCodeService.buscarAssociacoesPorUsuarioId(id);
    
    return new UsuarioCompletoResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getPontuacaoTotal(),
            qrCodes
    );
}

public Long getPontosTotais(UUID usuarioId) {
    return usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"))
            .getPontuacaoTotal();
}

public int contarCursosIniciados(UUID usuarioId) {
    return cursoUsuarioRepository.countByUsuarioId(usuarioId);
}
   
    public List<UsuarioCursoDTO> listarCursosComProgresso(UUID usuarioId) {
       
        List<CursoUsuario> cursosIniciados = cursoUsuarioRepository.findByUsuarioId(usuarioId);
    
        // Se a lista de cursos estiver vazia, retorna uma lista vazia
        if (cursosIniciados.isEmpty()) {
            return List.of(); // Retorna lista vazia se não houver cursos
        }
    
        // Mapeia os cursos para a lista de DTOs
        return cursosIniciados.stream()
                .map(cursoUsuario -> new UsuarioCursoDTO(
                        cursoUsuario.getCurso().getTitulo(),
                        cursoUsuario.getProgresso(),
                        cursoUsuario.getDataInicio()
                ))
                .collect(Collectors.toList());
    }

}




