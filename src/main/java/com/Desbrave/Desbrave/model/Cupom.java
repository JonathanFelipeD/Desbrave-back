package com.Desbrave.Desbrave.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.Desbrave.Desbrave.constants.TipoCupom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column( nullable =  false, length = 20)
    private String codigo;

    @Column(nullable = false, length = 20)
    private BigDecimal desconto;


    @Column(nullable = false)
    private TipoCupom tipoCupom;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


     @ManyToMany
    @JoinTable(
        name = "cupom_curso",
        joinColumns = @JoinColumn(name = "cupom_id"),
        inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Cursos> cursos = new ArrayList<>(); 





    
    
}
