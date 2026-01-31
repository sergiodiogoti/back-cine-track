package com.catalogo.filmes.mapper;

import com.catalogo.filmes.dto.FilmeRequest;
import com.catalogo.filmes.dto.FilmeResponse;
import com.catalogo.filmes.infra.elastic.document.FilmeDocument;
import com.catalogo.filmes.model.Filme;
import org.springframework.stereotype.Component;

@Component
public class FilmeMapper {

    public Filme toEntity(FilmeRequest dto) {
        Filme filme = new Filme();
        filme.setTitulo(dto.titulo());
        filme.setGenero(dto.genero());
        filme.setAno(dto.ano());
        filme.setNota(dto.nota());
        filme.setSinopse(dto.sinopse());
        return filme;
    }

    public FilmeResponse toDTO(Filme filme) {
        return new FilmeResponse(
                filme.getId(),
                filme.getTitulo(),
                filme.getGenero(),
                filme.getAno(),
                filme.getNota(),
                filme.getSinopse()
        );
    }

    public FilmeDocument toDocument(Filme filme) {
        return new FilmeDocument(
                filme.getId(),
                filme.getTitulo(),
                filme.getGenero(),
                filme.getSinopse(),
                filme.getAno(),
                filme.getNota()
        );
    }
}
