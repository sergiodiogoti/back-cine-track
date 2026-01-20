package com.catalogo.filmes.payload;

import java.util.Optional;

public class CriteriaRequest {

    private Optional<String> titulo;
    private Optional<String> genero;

    public CriteriaRequest(Optional<String> titulo, Optional<String> genero) {
        this.titulo = titulo;
        this.genero = genero;
    }

    public Optional<String> getTitulo() {
        return titulo;
    }

    public Optional<String> getGenero() {
        return genero;
    }
}
