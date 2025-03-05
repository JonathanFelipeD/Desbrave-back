package com.Desbrave.Desbrave.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioQrCodeRequest {

    private long usuarioId;
    private long qrCodeId;
    
}
