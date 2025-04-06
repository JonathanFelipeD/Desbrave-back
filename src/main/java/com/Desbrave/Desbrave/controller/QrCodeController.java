package com.Desbrave.Desbrave.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.model.QrCode;
import com.Desbrave.Desbrave.service.QrCodeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/qrcodes")
@RequiredArgsConstructor
@Tag(name = "QrCode", description = "endpoints para gerenciar qrcodes")
public class QrCodeController {

  
    private final QrCodeService qrCodeService;

    
    @PostMapping
    @Operation(summary = "Cadastrar QrCode")
    public ResponseEntity<QrCode> criarQrCode(@RequestBody QrCode qrCode) {
        if (qrCode.getId() != null) {
            return ResponseEntity.badRequest().body(null); 
        }
        QrCode novoQrCode = qrCodeService.criarQrCode(qrCode);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoQrCode);
    }

    
    @GetMapping
    @Operation(summary = "Listar QrCodes")
    public List<QrCode> listaQrCodes() {
        return qrCodeService.listarQrCodes();
    }

    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar QrCode por id")
    public ResponseEntity<QrCode> buscarQrCodePorId(@PathVariable Long id) {
        return qrCodeService.buscarQrCodePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar QrCode por id")
    public ResponseEntity<QrCode> atualizarQrCode(@PathVariable long id, @RequestBody QrCode qrCodeAtualizado) {
        return qrCodeService.atualizarQrCode(id, qrCodeAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar QrCode por id")
    public ResponseEntity<Object> deletarQrCode(@PathVariable long id) {
        if (qrCodeService.deletarQrCode(id)) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("QrCode não encontrado"); // Retorna 404 Not Found com uma mensagem
        }
    }
}
