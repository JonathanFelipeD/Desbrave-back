package com.Desbrave.Desbrave.config;





import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Desbrave.Desbrave.security.SecurityFilter;
import com.Desbrave.Desbrave.service.AutenticacaoService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@SecurityScheme(name = SecurityConfig.SECURITY, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfig {

    public static final String SECURITY = "bearerAuth";

    @SuppressWarnings("unused")
    private final AutenticacaoService autenticacaoService;

    
    private final SecurityFilter securityFilter;


    
    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService) throws Exception {
        return http
        .csrf().disable()
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/autenticacao/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/autenticacao/cadastrar").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/usuarios", "/cursos", "/qrcodes", "/cupom", "/parcerias", "/forum").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/cursos", "/qrcodes", "/cupom", "/parcerias", "/forum").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/usuarios" , "/cursos", "/qrcodes", "/cupom", "/parcerias", "/forum").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET,   "/cursos", "/qrcodes", "/cupom", "/parcerias", "/forum").hasRole("ADMIN")
            .requestMatchers("v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html/**").permitAll()
            .requestMatchers("/api/usuarios/recuperar-senha", "/api/usuarios/redefinir-senha").permitAll()
            .anyRequest().authenticated()
            )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }
    
  
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}