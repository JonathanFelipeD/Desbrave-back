package com.Desbrave.Desbrave.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Desbrave.Desbrave.DTO.ForumView;
import com.Desbrave.Desbrave.model.Forum;

public interface ForumRepository extends JpaRepository <Forum,Integer> {

    Optional<Forum> findById(Long forumId);
    

    @Query("SELECT f FROM Forum f LEFT JOIN f.usuario u")
List<ForumView> findAllProjected();
}
