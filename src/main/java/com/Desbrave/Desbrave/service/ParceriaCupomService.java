package com.Desbrave.Desbrave.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Desbrave.Desbrave.model.ParceriaCupom;
import com.Desbrave.Desbrave.repository.ParceriaCupomRepository;

@Service
public class ParceriaCupomService {

    private final ParceriaCupomRepository parceriaCupomRepository;

    public ParceriaCupomService(ParceriaCupomRepository parceriaCupomRepository) {
        this.parceriaCupomRepository = parceriaCupomRepository;
    }

    public ParceriaCupom cadastrarParceriaCupom(ParceriaCupom parceriaCupom) {
        return parceriaCupomRepository.save(parceriaCupom);
    }

    public List<ParceriaCupom> listarParcerias() {
        return parceriaCupomRepository.findAll();
    }

    public void deletarParceriaCupom(UUID id) {
        parceriaCupomRepository.deleteById(id);
    }

    public ParceriaCupom atualizarParceriaCupom(UUID id, ParceriaCupom parceriaCupom) {
        ParceriaCupom existente = parceriaCupomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cupom não encontrado."));

        existente.setValidade(parceriaCupom.getValidade());
        existente.setTipoParceriaCupom(parceriaCupom.getTipoParceriaCupom());

        return parceriaCupomRepository.save(existente);
    }

}
