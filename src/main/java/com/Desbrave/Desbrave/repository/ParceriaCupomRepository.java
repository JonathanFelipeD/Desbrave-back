package com.Desbrave.Desbrave.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Desbrave.Desbrave.model.ParceriaCupom;

public interface ParceriaCupomRepository  extends JpaRepository< ParceriaCupom ,UUID>{
    
}
