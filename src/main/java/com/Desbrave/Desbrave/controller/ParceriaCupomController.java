package com.Desbrave.Desbrave.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.Desbrave.Desbrave.model.Cupom;
import com.Desbrave.Desbrave.repository.CupomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.model.ParceriaCupom;
import com.Desbrave.Desbrave.repository.ParceriaCupomRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/parceriaCupom")
@Tag(name = "ParceriaCupom", description = "endpoints para gerenciar parcerias e cupons")
public class ParceriaCupomController {

    private final ParceriaCupomRepository parceriaCupomRepository;
    private final CupomRepository cupomRepository;



    @PostMapping
    @Operation(summary = "Cadastrar cupom de parceria")
    public ResponseEntity<ParceriaCupom> cadastrarParceriaCupom(@RequestBody ParceriaCupom parceriaCupom) {
        UUID cupomId = parceriaCupom.getCupom().getId();

        Cupom cupom = cupomRepository.findById(cupomId)
                .orElseThrow(() -> new RuntimeException("Cupom não encontrado com ID: " + cupomId));

        parceriaCupom.setCupom(cupom);
        ParceriaCupom novaParceriaCupom = parceriaCupomRepository.save(parceriaCupom);

        return ResponseEntity.ok(novaParceriaCupom);
    }

    @GetMapping
    @Operation(summary = "Listar cupons de parceria")
    public List<ParceriaCupom> listaParceriaCupoms() {
        return parceriaCupomRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar cupom de parceria")
    public void deletarParceriaCupom(@PathVariable("id") UUID idParceriaCupom) {
        parceriaCupomRepository.deleteById(idParceriaCupom);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cupom de parceria por id")
    public ParceriaCupom atualizarParceriaCupom(@PathVariable("id") UUID idParceriaCupom, @RequestBody ParceriaCupom parceriaCupom){
        Optional<ParceriaCupom> parceriaCupomExistente = parceriaCupomRepository.findById(idParceriaCupom);
        if(parceriaCupomExistente.isPresent()){
            ParceriaCupom parceriaCupom2 = parceriaCupomExistente.get();
            parceriaCupom2.setValidade(parceriaCupom.getValidade());
            parceriaCupom2.setTipoParceriaCupom(parceriaCupom.getTipoParceriaCupom());

            return parceriaCupomRepository.save(parceriaCupom2);
        }
        return null;
    }


}
