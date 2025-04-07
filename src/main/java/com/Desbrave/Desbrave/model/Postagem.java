package com.Desbrave.Desbrave.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Postagem")
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String conteudo;  

    @Column(nullable = false)
    private LocalDate dataPostagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @PrePersist
    protected void onCreate() {
        this.dataPostagem = LocalDate.now();
    }
}