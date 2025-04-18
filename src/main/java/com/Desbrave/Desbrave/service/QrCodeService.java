package com.Desbrave.Desbrave.service;


import com.Desbrave.Desbrave.model.QrCode;
import com.Desbrave.Desbrave.repository.QrCodeRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class QrCodeService {

private final QrCodeRepository qrCodeRepository;

    public QrCodeService(QrCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    @Transactional
    public QrCode criarQrCode(QrCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }

    public Optional<QrCode> atualizarQrCode(UUID id, QrCode qrCodeAtualizado) {
        return qrCodeRepository.findById(id).map(qrCode -> {
            qrCode.setCodigo(qrCodeAtualizado.getCodigo());
            return qrCodeRepository.save(qrCode);
        });
    }

    public List<QrCode> listarQrCodes() {
        return qrCodeRepository.findAll();
    }

    public Optional<QrCode> buscarQrCodePorId(UUID id) {
        return qrCodeRepository.findById(id);
    }

    

    public boolean deletarQrCode(UUID id) {
        return qrCodeRepository.findById(id).map(qrCode -> {
            qrCodeRepository.delete(qrCode);
            return true;
        }).orElse(false);
    }
}
