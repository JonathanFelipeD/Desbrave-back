package com.Desbrave.Desbrave.security;

import java.io.IOException;
import java.util.Optional;


import com.Desbrave.Desbrave.model.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.Desbrave.Desbrave.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    
    private final TokenService tokenService;

  
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);

        if(token != null){
            try {
                var login = tokenService.validacaoToken(token);
                Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(login);

                if (usuarioOptional.isPresent()) {
                    Usuario usuario = usuarioOptional.get();


                    String authority = "ROLE_" + usuario.getTipoUsuario().name();

                    UserDetails userDetails = User.withUsername(usuario.getEmail())
                            .password(usuario.getSenha())
                            .accountExpired(false)
                            .authorities(authority)
                            .build();

                    var autenticacao = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();

            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var autorizacaoHeader = request.getHeader("Authorization");
        if(autorizacaoHeader == null){return null;}
        return autorizacaoHeader.replace("Bearer ", "");
    }
}
