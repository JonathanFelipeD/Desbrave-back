package com.Desbrave.Desbrave.model;


import com.Desbrave.Desbrave.constants.Usado;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class UsuarioCupom {

    @Id
    @GeneratedValue
    private UUID idUsuarioCupom;

    @Column(nullable =  false)
    private LocalDate dataResgate;

    @Column(nullable = false)
    private Usado Usado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cupom_id")
    private Cupom cupom;


    @PrePersist
    protected void onCreate(){
        this.dataResgate = LocalDate.now();
    }


    public UsuarioCupom adicionarCupomAoUsuario(UUID usuarioId, UsuarioCupom cupom2) {
       
        throw new UnsupportedOperationException("Unimplemented method 'adicionarCupomAoUsuario'");
    }

}
