package com.Desbrave.Desbrave.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Desbrave.Desbrave.model.Cupom;

public interface CupomRepository  extends JpaRepository<Cupom,Long >{
    Optional<Cupom> findByCodigo(String codigo);
    
}
