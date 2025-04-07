package com.Desbrave.Desbrave.DTO;

import java.util.List;
import java.util.UUID;

public record UsuarioCompletoResponse(
        UUID id,
        String nome,
        String email,
        Long pontosTotais,
        List<UsuarioQrCodeResponse> qrCodesAssociados
) {}