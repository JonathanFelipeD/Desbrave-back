package com.Desbrave.Desbrave.repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Desbrave.Desbrave.DTO.ForumView;
import com.Desbrave.Desbrave.model.Forum;

public interface ForumRepository extends JpaRepository<Forum, UUID> {




       @Query("SELECT f FROM Forum f LEFT JOIN FETCH f.usuario")
       List<Forum> findAllWithUsuario();

    List<Forum> findByUsuario_Id(UUID usuarioId);

    Optional<Forum> findByTitulo(String titulo);


}