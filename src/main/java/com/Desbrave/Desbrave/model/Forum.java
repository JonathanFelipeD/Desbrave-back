package com.Desbrave.Desbrave.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Desbrave.Desbrave.constants.Titulo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idforum")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "titulo", nullable = false)
    private Titulo titulo;

    @Column(name = "descricao", length = 25, nullable = false)
    private String descricao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "usuario_idusuario", nullable = false)
    private Integer usuarioId;

    @OneToMany(mappedBy = "forum")
    private List<Postagem> postagens = new ArrayList<>();


}

