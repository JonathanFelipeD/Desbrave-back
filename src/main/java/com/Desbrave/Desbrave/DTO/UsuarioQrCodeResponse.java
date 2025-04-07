package com.Desbrave.Desbrave.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioQrCodeResponse {
    private UUID id;
    private UsuarioResponse usuario;
    private QrCodeResponse qrCode;
    private LocalDateTime dataEscaneamento;
}