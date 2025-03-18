package com.Desbrave.Desbrave.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "/cupom")
public class CupomController {

    @PostMapping
    public Cupom cadastrarCupom(@RequestBody Cupom idCupom) {
        return cupomRepository.save(idCupom);
    }

    @GetMapping
    public List<Cupom> listaCupons(){
        return cupomRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletarCupom(@PathVariable("id") Long idCupom){
        cupomRepository.deleteById(idCupom);    
    }

    @PutMapping("/{id}")
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




    @Autowired
    private CupomRepository cupomRepository;
}
