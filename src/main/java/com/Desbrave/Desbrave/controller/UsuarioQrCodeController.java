package com.Desbrave.Desbrave.controller;
import java.util.List;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.DTO.UsuarioQrCodeRequest;
import com.Desbrave.Desbrave.model.UsuarioQrCode;
import com.Desbrave.Desbrave.service.UsuarioQrCodeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/usuario-qrcode")
@RequiredArgsConstructor
@Tag(name = "UsuarioQrCode", description = "endpoints para gerenciar associacoes de usuario com qrcode")
public class UsuarioQrCodeController {

    
    private final UsuarioQrCodeService usuarioQrCodeService;

    
    @PostMapping
    @Operation(summary = "Associar Usuario a QrCode")
    public UsuarioQrCode associarUsuarioQrCode(@RequestBody UsuarioQrCodeRequest request) {
        return usuarioQrCodeService.associarUsuarioQrCode(request.getUsuarioId(), request.getQrCodeId());
    }

    
    @GetMapping
    @Operation(summary = "Listar Associacoes")
    public List<UsuarioQrCode> listarAssociacoes() {
        return usuarioQrCodeService.listarAssociacoes();
    }

    
    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar Associacoes por UsuarioId")
    public List<UsuarioQrCode> buscarAssociacoesPorUsuarioId(@PathVariable Long usuarioId) {
        return usuarioQrCodeService.buscarAssociacoesPorUsuarioId(usuarioId);
    }

   
    @GetMapping("/qrcode/{qrCodeId}")
    @Operation(summary = "Buscar Associacoes por QrCodeId")
    public List<UsuarioQrCode> buscarAssociacoesPorQrCodeId(@PathVariable Long qrCodeId) {
        return usuarioQrCodeService.buscarAssociacoesPorQrCodeId(qrCodeId);
    }
}
