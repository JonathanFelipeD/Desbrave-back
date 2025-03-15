package com.Desbrave.Desbrave.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Desbrave.Desbrave.model.ParceriaCupom;
import com.Desbrave.Desbrave.repository.ParceriaCupomRepository;

@RestController
@RequestMapping(value = "/ParceriaCupom")
public class ParceriaCupomController {

    @PostMapping
    public ParceriaCupom cadastrarParceriaCupom(@RequestBody ParceriaCupom idParceriaCupom) {

        return parceriaCupomRepository.save(idParceriaCupom);
    }

    @GetMapping
    public List<ParceriaCupom> listaParceriaCupoms() {
        return parceriaCupomRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletarParceriaCupom(@PathVariable("id") Long idParceriaCupom) {
        parceriaCupomRepository.deleteById(idParceriaCupom);
    }

    public ParceriaCupom atualizarParceriaCupom(@PathVariable("id") Long idParceriaCupom, @RequestBody ParceriaCupom parceriaCupom){
        Optional<ParceriaCupom> parceriaCupomExistente = parceriaCupomRepository.findById(idParceriaCupom);
        if(parceriaCupomExistente.isPresent()){
            ParceriaCupom parceriaCupom2 = parceriaCupomExistente.get();
            parceriaCupom2.setValidade(parceriaCupom.getValidade());
            parceriaCupom2.setTipoParceriaCupom(parceriaCupom.getTipoParceriaCupom());

            return parceriaCupomRepository.save(parceriaCupom2);
        }
        return null;
    }

    @Autowired
    private ParceriaCupomRepository parceriaCupomRepository;
}
