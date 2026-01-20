package com.catalogo.filmes.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Table(name = "filmes")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Filme{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String genero;
    private Integer ano;
    private BigDecimal nota;

    @Column(columnDefinition = "TEXT")
    private String sinopse;
}
