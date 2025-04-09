package com.Desbrave.Desbrave.repository;


import com.Desbrave.Desbrave.model.MensagemForum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MensagemForumRepository extends JpaRepository<MensagemForum, UUID> {
    List<MensagemForum> findByForumIdOrderByDataEnvioAsc(UUID forumId);
}