package com.Desbrave.Desbrave.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Desbrave.Desbrave.model.Parceria;


public interface ParceriaRepository extends JpaRepository<Parceria, UUID> {
}