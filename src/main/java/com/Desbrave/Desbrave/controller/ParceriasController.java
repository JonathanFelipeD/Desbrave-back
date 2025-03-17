package com.Desbrave.Desbrave.controller;

import com.Desbrave.Desbrave.model.Parceria;
import com.Desbrave.Desbrave.service.ParceriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parcerias")
public class ParceriasController {
    @Autowired
    private ParceriaService parceriaService;

    // Endpoint para listar todas as parcerias
    @GetMapping
    public ResponseEntity<List<Parceria>> getAllParcerias() {
        List<Parceria> parcerias = parceriaService.listarTodas();
        return ResponseEntity.ok(parcerias);
    }

    // Endpoint para buscar uma parceria pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Parceria> getParceriaById(@PathVariable Integer id) {
        Optional<Parceria> parceria = parceriaService.buscarPorId(id);
        return parceria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar uma nova parceria
    @PostMapping
    public ResponseEntity<Parceria> createParceria(@RequestBody Parceria parceria) {
        Parceria createdParceria = parceriaService.salvar(parceria);
        return ResponseEntity.ok(createdParceria);
    }

    // Endpoint para atualizar uma parceria existente
    @PutMapping("/{id}")
    public ResponseEntity<Parceria> updateParceria(@PathVariable Integer id, @RequestBody Parceria parceriaDetails) {
        try {
            Parceria updatedParceria = parceriaService.atualizar(id, parceriaDetails);
            return ResponseEntity.ok(updatedParceria);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para excluir uma parceria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParceria(@PathVariable Integer id) {
        try {
            parceriaService.deletar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}