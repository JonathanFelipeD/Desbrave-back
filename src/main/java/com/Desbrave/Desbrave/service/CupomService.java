package com.Desbrave.Desbrave.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.Desbrave.Desbrave.exception.DescontoLimitadoException;
import com.Desbrave.Desbrave.model.Cupom;
import com.Desbrave.Desbrave.repository.CupomRepository;
import org.slf4j.Logger;

@Service
public class CupomService {

    private static final Logger logger = LoggerFactory.getLogger(CupomService.class);

    private final CupomRepository cupomRepository;

    public CupomService(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public Cupom criarCupom(Cupom cupom) {
        if (cupomRepository.findByCodigo(cupom.getCodigo()).isPresent()) {
            throw new RuntimeException("Cupom já cadastrado com esse código.");
        }
        return cupomRepository.save(cupom);
    }

    public Cupom buscarPorCodigo(String codigo) {
        return cupomRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Cupom não Encontrado"));
    }

    

    public void deletarCupom(UUID id) {
        if (!cupomRepository.existsById(id)) {
            throw new RuntimeException("Cupom não encontrado para Deletar.");
        }
        cupomRepository.deleteById(id);
    }

    public BigDecimal calcularDesconto(String codigo, BigDecimal valorCompra) {
        Cupom cupom = buscarPorCodigo(codigo);
        BigDecimal desconto = cupom.getDesconto();

        if (desconto.compareTo(valorCompra) > 0) {
            throw new DescontoLimitadoException("O desconto foi limitado ao valor da compra.");
        } else {
            logger.info("Desconto de R${} aplicado com sucesso.", desconto);
            return desconto;
        }

    }

    public List<Cupom> listarCupons() {
        return cupomRepository.findAll();
    }

    public Cupom atualizarCupom(UUID idCupom, Cupom cupom) {
        Optional<Cupom> cupomExistente = cupomRepository.findById(idCupom);
        if (cupomExistente.isPresent()) {
            Cupom cupomObj = cupomExistente.get();
            cupomObj.setCodigo(cupom.getCodigo());
            cupomObj.setDesconto(cupom.getDesconto());
            cupomObj.setTipoCupom(cupom.getTipoCupom());
            
            // Atualize cursos e parcerias se fornecidos
            if (cupom.getCursos() != null) {
                cupomObj.setCursos(cupom.getCursos());
            }
            
             if (cupom.getParcerias() != null) {
                 cupomObj.setParcerias(cupom.getParcerias());
             }
    
             
            return cupomRepository.save(cupomObj);
        }
        throw new RuntimeException("Cupom não encontrado para atualização.");
    }
}