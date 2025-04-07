package com.Desbrave.Desbrave.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.Desbrave.Desbrave.model.Parceria;
import com.Desbrave.Desbrave.repository.ParceriaRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ParceriaService {
    

    private final ParceriaRepository parceriaRepository;
    public List<Parceria> listarTodas() {
        return parceriaRepository.findAll();
    }
    public Optional<Parceria> buscarPorId(UUID id) {
        return parceriaRepository.findById(id);
    }
    public Parceria salvar(Parceria parceria) {
        return parceriaRepository.save(parceria);
    }
    public Parceria atualizar(UUID id, Parceria parceriaAtualizada) {
        if (parceriaRepository.existsById(id)) {
            parceriaAtualizada.setIdParceria(id);
            return parceriaRepository.save(parceriaAtualizada);
        }
        throw new RuntimeException("Parceria não encontrada");
    }
    public void deletar(UUID id) {
        if (parceriaRepository.existsById(id)) {
            parceriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Parceria não encontrada");
        }
    }
}