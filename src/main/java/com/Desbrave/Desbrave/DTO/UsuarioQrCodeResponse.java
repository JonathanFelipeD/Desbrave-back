package com.Desbrave.Desbrave.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioQrCodeResponse {
    private Long id;
    private UsuarioResponse usuario;
    private QrCodeResponse qrCode;
    private LocalDateTime dataEscaneamento;
}