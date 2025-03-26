package com.Desbrave.Desbrave.service;

import com.Desbrave.Desbrave.DTO.GoogleOAuth2User;
import com.Desbrave.Desbrave.constants.TipoUsuario;
import com.Desbrave.Desbrave.model.Usuario;
import com.Desbrave.Desbrave.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsuarioRepository usuarioRepository;

    public  CustomOAuth2UserService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oauth2User = super.loadUser(userRequest);
        GoogleOAuth2User googleUser = new GoogleOAuth2User(oauth2User.getAttributes());

        Usuario usuario = usuarioRepository.findByEmail(googleUser.getEmail())
                .orElseGet(() -> {
                    Usuario novoUsuario = new Usuario();
                    novoUsuario.setEmail(googleUser.getEmail());
                    novoUsuario.setNomeCompleto(googleUser.getName());
                    novoUsuario.setTipoUsuario(TipoUsuario.USUARIO_COMUM);
                    return usuarioRepository.save(novoUsuario);
                });


        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getTipoUsuario()));

        return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), "email");
    }

    }

