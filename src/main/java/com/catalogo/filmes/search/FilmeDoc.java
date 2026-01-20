package com.catalogo.filmes.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmeDoc {

    private Long id;
    private String titulo;
    private String genero;
    private Integer ano;
    private Double nota;
    private String sinopse;
    private Double score;
    private String sinopseFormatada;
}

