package com.Desbrave.Desbrave.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.model.Cupom;
import com.Desbrave.Desbrave.repository.CupomRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/cupom")
@RequiredArgsConstructor
@Tag(name = "Cupom", description = "Endpoints para gerenciamento de cupons")
public class CupomController {

    @PostMapping
    @Operation(summary = "Cadastrar um cupom", description = "Cadastra um novo cupom")
    public Cupom cadastrarCupom(@RequestBody Cupom idCupom) {
        return cupomRepository.save(idCupom);
    }

    @GetMapping
    @Operation(summary = "Listar cupons", description = "Lista todos os cupons cadastrados")
    public List<Cupom> listaCupons(){
        return cupomRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um cupom", description = "Deleta um cupom pelo id")
    public void deletarCupom(@PathVariable("id") Long idCupom){
        cupomRepository.deleteById(idCupom);    
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um cupom", description = "Atualiza um cupom pelo id")
    public Cupom atualizarCupomPeloId(@PathVariable("id") Long idCupom, @RequestBody Cupom cupom){
        Optional<Cupom> cupomExistente = cupomRepository.findById(idCupom);
        if(cupomExistente.isPresent()){
            Cupom cupomObj = cupomExistente.get();
            cupomObj.setCodigo(cupom.getCodigo());
            cupomObj.setDesconto(cupom.getDesconto());
            cupomObj.setTipoCupom(cupom.getTipoCupom());

            return cupomRepository.save(cupomObj);

        }
        return null;
        
    }




   
    private final CupomRepository cupomRepository;
}
