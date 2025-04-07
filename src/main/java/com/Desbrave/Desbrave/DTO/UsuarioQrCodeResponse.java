package com.Desbrave.Desbrave.DTO;


import java.time.LocalDateTime;
import java.util.UUID;


public record UsuarioQrCodeResponse (
    UUID id,
    UsuarioResponse usuario,
    QrCodeResponse qrCode,
    LocalDateTime dataEscaneamento,
    Long pontosGanhos
) {}


