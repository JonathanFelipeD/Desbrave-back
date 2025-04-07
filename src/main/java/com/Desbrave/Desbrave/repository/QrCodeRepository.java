package com.Desbrave.Desbrave.repository;



import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Desbrave.Desbrave.model.QrCode;


public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {

    

}
