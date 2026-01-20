package com.catalogo.filmes.dto;

import java.math.BigDecimal;

public record FilmeResponse(
        Long id,
        String titulo,
        String genero,
        Integer ano,
        BigDecimal nota,
        String sinopse
) {}