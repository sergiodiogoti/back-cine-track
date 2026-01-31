package com.catalogo.filmes.service;

import com.catalogo.filmes.dto.FilmeRequest;
import com.catalogo.filmes.dto.FilmeResponse;
import com.catalogo.filmes.exception.ResourceNotFoundException;
import com.catalogo.filmes.infra.elastic.document.FilmeDocument;
import com.catalogo.filmes.mapper.FilmeMapper;
import com.catalogo.filmes.model.Filme;
import com.catalogo.filmes.repository.FilmeElasticRepository;
import com.catalogo.filmes.repository.FilmeRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final FilmeElasticRepository filmeElasticRepository;
    private final FilmeMapper mapper;

    public FilmeService(
            FilmeRepository filmeRepository,
            FilmeElasticRepository filmeElasticRepository,
            FilmeMapper mapper
    ) {
        this.filmeRepository = filmeRepository;
        this.filmeElasticRepository = filmeElasticRepository;
        this.mapper = mapper;
    }

    @Transactional
    public FilmeResponse salvar(FilmeRequest dto) {
        Filme filme = mapper.toEntity(dto);
        Filme salvo = filmeRepository.save(filme);

        FilmeDocument filmeDocument = mapper.toDocument(salvo);
        filmeElasticRepository.save(filmeDocument);

        return mapper.toDTO(salvo);
    }

    @Transactional
    public FilmeResponse atualizar(Long id, FilmeRequest dto) {
        if (!filmeRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Filme com id: %s não foi encontrado na base de dados ",id));
        }

        Filme filme = mapper.toEntity(dto);
        filme.setId(id);

        Filme filmeAtualizado = filmeRepository.save(filme);

        FilmeDocument filmeDocument = mapper.toDocument(filmeAtualizado);
        filmeElasticRepository.save(filmeDocument);

        return mapper.toDTO(filmeAtualizado);
    }

    public FilmeResponse buscarPorId(Long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Filme com id: %s não foi encontrado na base de dados ",id))
                );
        return mapper.toDTO(filme);
    }


    public void deletar(Long id) {
        if (!filmeRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Filme com id: %s não foi encontrado na base de dados ",id));
        }
        filmeRepository.deleteById(id);
    }

    public Page<FilmeResponse> getAllPaginado(int page, int size) {

        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by("titulo").ascending()
        );

        Page<Filme> pageResult =
                filmeRepository.findAll(pageRequest);

        return pageResult.map(mapper::toDTO);
    }
}
