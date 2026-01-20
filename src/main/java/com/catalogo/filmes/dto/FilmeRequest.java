package com.catalogo.filmes.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FilmeRequest(

        @NotBlank(message = "Título é obrigatório")
        String titulo,

        @NotBlank(message = "Gênero é obrigatório")
        String genero,

        @NotNull(message = "Ano é obrigatório")
        @Min(value = 1888, message = "Ano inválido")
        Integer ano,

        @NotNull(message = "Nota é obrigatória")
        @Min(value = 0, message = "Nota mínima é 0")
        @Max(value = 10, message = "Nota máxima é 10")
        BigDecimal nota,

        String sinopse
) {}
