package com.Desbrave.Desbrave.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(String emailUsuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            
            String token = JWT.create()
                .withIssuer("Desbrave")
                .withSubject(emailUsuario)
                .withExpiresAt(genExpirationDate())
                .sign(algorithm);
                return token;
        }catch(JWTCreationException exception){
            throw new RuntimeException("Erro na geração do token", exception);

        }
    }


    public String validacaoToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("Desbrave")
            .build()
            .verify(token)
            .getSubject();
        }catch(JWTCreationException exception){}
        return "";
        }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
