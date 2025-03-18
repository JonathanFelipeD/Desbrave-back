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
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @SuppressWarnings({ "removal" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        .csrf().disable()
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/autenticacao/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/autenticacao/cadastrar").permitAll()
            .requestMatchers(HttpMethod.POST, "/cursos", "/qrcodes", "/cupom", "/parcerias", "/forum").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/cursos", "/qrcodes", "/cupom", "/parcerias", "/forum").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/usuarios" , "/cursos", "/qrcodes", "/cupom", "/parcerias", "/forum").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET,   "/cursos", "/qrcodes", "/cupom", "/parcerias", "/forum").hasRole("ADMIN")
            .anyRequest().authenticated()
            )
        .build();
    }
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}