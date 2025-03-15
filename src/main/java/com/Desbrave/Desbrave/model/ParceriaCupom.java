package com.Desbrave.Desbrave.model;

import java.time.LocalDateTime;

import com.Desbrave.Desbrave.constants.TipoParceriaCupom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ParceriaCupom")
public class ParceriaCupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParceriaCupom;

    @Column(nullable = false)
    private LocalDateTime validade;

    @Column(nullable = false)
    private TipoParceriaCupom tipoParceriaCupom;

    @ManyToOne
    @JoinColumn(name="Cupom_id")
    private Cupom cupom;


}
