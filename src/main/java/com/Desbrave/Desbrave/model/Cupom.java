package com.Desbrave.Desbrave.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.Desbrave.Desbrave.constants.TipoCupom;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Cupom")
public class Cupom {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 20)
    private String codigo;

    @Column(nullable = false, length = 20)
    private BigDecimal desconto;

    @Column(nullable = false)
    private TipoCupom tipoCupom;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "cupom_curso",
            joinColumns = @JoinColumn(name = "cupom_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Cursos> cursos = new ArrayList<>();

    //@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   // @JoinTable(
           // name = "cupom_parceria",
          //  joinColumns = @JoinColumn(name = "cupom_id"),
          //  inverseJoinColumns = @JoinColumn(name = "parceria_id")
    //)
   // private List<Parceria> parcerias = new ArrayList<>();
}
