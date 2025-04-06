package com.Desbrave.Desbrave.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.Desbrave.Desbrave.constants.Titulo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forum")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Titulo titulo;

    @Column(length = 500) 
    private String descricao;

    @CreationTimestamp 
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao; 


    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Postagem> postagens = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false) 
    private Usuario usuario;

    // Método para serializar apenas o ID
    public Long getUsuarioId() {
        return this.usuario != null ? this.usuario.getId() : null;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
   
}

