package com.Desbrave.Desbrave.model;

import com.Desbrave.Desbrave.constants.TipoCupom;

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
    private Double desconto;


    @Column(nullable = false)
    private TipoCupom tipoCupom;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;






    
    
}
