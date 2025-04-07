package com.Desbrave.Desbrave.model;

import com.Desbrave.Desbrave.constants.TipoParceria;
import com.Desbrave.Desbrave.constants.StatusParceria;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;
// ... outros imports ...

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parceria")
public class Parceria {

    @Id
    @GeneratedValue
    @Column(name = "idParceria")
    private UUID idParceria;

    @Column(nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoParceria tipo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 100)
    private String contato;

    @Column(length = 100)
    private String localizacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusParceria status;

    @ManyToMany(mappedBy = "parcerias")
    @JsonBackReference
    private List<Cupom> cupons;
}
