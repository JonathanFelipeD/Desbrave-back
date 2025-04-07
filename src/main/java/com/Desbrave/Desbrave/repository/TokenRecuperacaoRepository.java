package com.Desbrave.Desbrave.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Desbrave.Desbrave.model.TokenRecuperacao;

public interface TokenRecuperacaoRepository extends JpaRepository<TokenRecuperacao,UUID> {
    TokenRecuperacao findByToken(String token);
    TokenRecuperacao findByEmail(String email);
    void deleteByEmail(String email);
}