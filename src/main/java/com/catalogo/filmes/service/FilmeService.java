package com.catalogo.filmes.service;

import com.catalogo.filmes.dto.FilmeRequest;
import com.catalogo.filmes.dto.FilmeResponse;
import com.catalogo.filmes.dto.PageResponse;
import com.catalogo.filmes.exception.ResourceNotFoundException;
import com.catalogo.filmes.mapper.FilmeMapper;
import com.catalogo.filmes.model.Filme;
import com.catalogo.filmes.payload.CriteriaRequest;
import com.catalogo.filmes.repository.FilmeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final EntityManager entityManager;
    private final FilmeMapper mapper;

    public FilmeService(
            FilmeRepository filmeRepository,
            EntityManager entityManager,
            FilmeMapper mapper
    ) {
        this.filmeRepository = filmeRepository;
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    public FilmeResponse salvar(FilmeRequest dto) {
        Filme filme = mapper.toEntity(dto);
        Filme salvo = filmeRepository.save(filme);
        return mapper.toDTO(salvo);
    }

    public FilmeResponse buscarPorId(Long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Filme não encontrado com id: " + id)
                );
        return mapper.toDTO(filme);
    }

    public FilmeResponse atualizar(Long id, FilmeRequest dto) {
        if (!filmeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Filme não encontrado com id: " + id);
        }

        Filme filme = mapper.toEntity(dto);
        filme.setId(id);

        return mapper.toDTO(filmeRepository.save(filme));
    }

    public void deletar(Long id) {
        if (!filmeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Filme não encontrado com id: " + id);
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

    public List<FilmeResponse> search(CriteriaRequest criteriaRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Filme> cq = cb.createQuery(Filme.class);
        Root<Filme> filme = cq.from(Filme.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteriaRequest.getTitulo().isPresent()) {
            predicates.add(
                    cb.like(
                            cb.lower(filme.get("titulo")),
                            "%" + criteriaRequest.getTitulo().get().toLowerCase() + "%"
                    )
            );
        }

        if (criteriaRequest.getGenero().isPresent()) {
            predicates.add(
                    cb.equal(
                            filme.get("genero"),
                            criteriaRequest.getGenero().get()
                    )
            );
        }

        cq.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(cq)
                .getResultList()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public long count() {
        return filmeRepository.count();
    }
}
