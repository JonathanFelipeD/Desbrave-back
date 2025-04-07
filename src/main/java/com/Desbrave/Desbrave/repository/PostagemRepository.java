package com.Desbrave.Desbrave.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Desbrave.Desbrave.model.Postagem;

public interface PostagemRepository extends JpaRepository < Postagem, UUID>{
    
}
