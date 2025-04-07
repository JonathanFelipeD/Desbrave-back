package com.Desbrave.Desbrave.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioQrCodeRequest {

    private UUID usuarioId;
    private UUID qrCodeId;
    
}
