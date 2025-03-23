package com.Desbrave.Desbrave.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioQrCodeRequest {

    private long usuarioId;
    private long qrCodeId;
    
}
