package com.Desbrave.Desbrave.model;

import java.time.LocalDate;

import com.Desbrave.Desbrave.constants.TipoResgate;

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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class HistoricoResgate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataResgate;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "tipo_resgate")
    private TipoResgate tipoResgate;

    //  @ManyToOne
    //  @JoinColumn(name = "parceriaCupom_id")
    //  private ParceriaCupom parceriaCupom;

    @ManyToOne
    @JoinColumn(name = "cupom_id")
    private Cupom cupom;
}
