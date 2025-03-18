package com.Desbrave.Desbrave.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
        .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/cursos", "/qrcodes"
        , "/cupom" , "/parcerias").hasRole("ADMIN")
        .anyRequest().authenticated())

        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/cursos", "/qrcodes", "/cupom", "/parcerias").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/cursos", "/qrcodes", "/cupom", "/parcerias").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/cursos", "/qrcodes", "/cupom", "/parcerias").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/qrcodes").hasRole("ADMIN")
            .anyRequest().authenticated())
        .build();
    }
  
}