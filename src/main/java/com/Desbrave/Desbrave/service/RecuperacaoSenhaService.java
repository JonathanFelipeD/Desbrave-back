// package com.Desbrave.Desbrave.service;

// //import com.Desbrave.Desbrave.model.Usuario;
// import com.Desbrave.Desbrave.repository.UsuarioRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;

// import java.security.SecureRandom;
// import java.time.LocalDate;
// import java.util.Base64;

// @Service
// @RequiredArgsConstructor
// public class RecuperacaoSenhaService {

//     private final UsuarioRepository usuarioRepository;
//     private final EmailService emailService;

//     // Método para gerar um token seguro
//     private String gerarToken() {
//         SecureRandom secureRandom = new SecureRandom();
//         byte[] tokenBytes = new byte[24];
//         secureRandom.nextBytes(tokenBytes);
//         return Base64.getUrlEncoder().encodeToString(tokenBytes);
//     }

//     // Envia o token de recuperação via e-mail para o usuário
//     public void enviarTokenRecuperacao(String email) {
//         Usuario usuario = usuarioRepository.findByEmail(email);
//         if (usuario == null) {
//             throw new IllegalArgumentException("Usuário não encontrado com o email: " + email);
//         }
//         // Gera o token e define a data de expiração (por exemplo, expira no dia seguinte)
//         String token = gerarToken();
//         usuario.setTokenRecuperacao(token);
//         usuario.setTokenExpiracao(LocalDate.now().plusDays(1)); // Token expira amanhã
//         usuarioRepository.save(usuario);

//         // Monta o link de recuperação (ajuste a URL conforme sua aplicação)
//         String linkRecuperacao = "http://localhost:8080/auth/redefinir-senha?token=" + token;
//         emailService.enviarEmail(usuario.getEmail(), "Recuperação de Senha",
//                 "Clique no link para redefinir sua senha: " + linkRecuperacao);
//     }

//     // Redefine a senha do usuário utilizando o token de recuperação
//     public void redefinirSenha(String token, String novaSenha) {
//         Usuario usuario = usuarioRepository.findByTokenRecuperacao(token);
//         if (usuario == null) {
//             throw new IllegalArgumentException("Token inválido.");
//         }
//         // Verifica se o token não expirou usando LocalDate
//         if (usuario.getTokenExpiracao() == null || usuario.getTokenExpiracao().isBefore(LocalDate.now())) {
//             throw new IllegalArgumentException("Token expirado.");
//         }
//         // Atualiza a senha do usuário (considere criptografar a senha)
//         usuario.setSenha(novaSenha);
//         // Invalida o token após o uso
//         usuario.setTokenRecuperacao(null);
//         usuario.setTokenExpiracao(null);
//         usuarioRepository.save(usuario);
//     }
// }
