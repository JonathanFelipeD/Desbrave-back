package com.Desbrave.Desbrave.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QrCodeResponse {
    private Long id;
    private String codigo;

    public QrCodeResponse(Long id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }
}