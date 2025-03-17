package com.Desbrave.Desbrave.model;
import com.Desbrave.Desbrave.constants.TipoParceria;
import com.Desbrave.Desbrave.constants.StatusParceria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parceria")
public class Parceria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idParceria")
    private Integer idParceria;
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
}


