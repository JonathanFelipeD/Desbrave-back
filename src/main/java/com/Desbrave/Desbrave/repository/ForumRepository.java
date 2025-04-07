package com.Desbrave.Desbrave.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Desbrave.Desbrave.DTO.ForumView;
import com.Desbrave.Desbrave.model.Forum;

public interface ForumRepository extends JpaRepository <Forum,UUID> {

    @Query("SELECT f FROM Forum f LEFT JOIN f.usuario u")
List<ForumView> findAllProjected();
}
