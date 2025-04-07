package com.Desbrave.Desbrave.controller;

import com.Desbrave.Desbrave.DTO.UsuarioQrCodeRequest;
import com.Desbrave.Desbrave.DTO.UsuarioQrCodeResponse;
import com.Desbrave.Desbrave.service.UsuarioQrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario-qrcode")
@RequiredArgsConstructor
public class UsuarioQrCodeController {

    private final UsuarioQrCodeService service;

    @PostMapping
    public UsuarioQrCodeResponse associar(@RequestBody UsuarioQrCodeRequest request) {
        return service.associarUsuarioQrCode(request);
    }

    @GetMapping
    public List<UsuarioQrCodeResponse> listarTodos() {
        return service.listarAssociacoes();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<UsuarioQrCodeResponse> buscarPorUsuario(@PathVariable UUID usuarioId) {
        return service.buscarAssociacoesPorUsuarioId(usuarioId);
    }

    @GetMapping("/qrcode/{qrCodeId}")
    public List<UsuarioQrCodeResponse> buscarPorQrCode(@PathVariable UUID qrCodeId) {
        return service.buscarAssociacoesPorQrCodeId(qrCodeId);
    }
}