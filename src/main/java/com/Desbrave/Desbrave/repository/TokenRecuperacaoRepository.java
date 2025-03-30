package com.Desbrave.Desbrave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Desbrave.Desbrave.model.TokenRecuperacao;

public interface TokenRecuperacaoRepository extends JpaRepository<TokenRecuperacao, Long> {
    TokenRecuperacao findByToken(String token);
    TokenRecuperacao findByEmail(String email);
    void deleteByEmail(String email);
}