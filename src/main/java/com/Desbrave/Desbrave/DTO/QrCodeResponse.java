package com.Desbrave.Desbrave.DTO;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QrCodeResponse {
    private UUID id;
    private String codigo;

    public QrCodeResponse(UUID id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }
}