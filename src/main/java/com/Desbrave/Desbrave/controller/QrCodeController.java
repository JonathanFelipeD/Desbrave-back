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
import com.Desbrave.Desbrave.repository.QrCodeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/qrcodes")
@RequiredArgsConstructor
public class QrCodeController {

   
    private final QrCodeRepository qrCodeRepository;

    //criar um qrcode
    @PostMapping
    public QrCode criarQrCode(@RequestBody QrCode qrCode){
        return qrCodeRepository.save(qrCode);
    }

    //listar os qrcode
    @GetMapping
    public List<QrCode> listaQrCodes(){
        return qrCodeRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<QrCode> buscarQrCodePorId(@PathVariable Long id){
        return qrCodeRepository.findById(id)
        .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        
    }

    //atualiza um qrCode pelo id
    @PutMapping("/{id}")
    public ResponseEntity<QrCode> atualizarQrCode(@PathVariable long id, @RequestBody QrCode qrCodeAtualizado){
        return qrCodeRepository.findById(id).map(qrCode -> {
            qrCode.setCodigo(qrCodeAtualizado.getCodigo());
            QrCode qrCodeSalvo = qrCodeRepository.save(qrCode);
            return ResponseEntity.ok(qrCodeSalvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    //deletar qrCode por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarQrCode(@PathVariable long id) {
        return qrCodeRepository.findById(id)
                .map(qrCode -> {
                    qrCodeRepository.delete(qrCode); // Exclui o objeto
                    return ResponseEntity.noContent().build(); // Retorna 204 No Content
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("QrCode não encontrado")); // Retorna 404 Not Found com uma mensagem
    }
}
