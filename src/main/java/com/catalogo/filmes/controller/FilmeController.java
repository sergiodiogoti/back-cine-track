package com.catalogo.filmes.controller;

import com.catalogo.filmes.infra.elastic.document.FilmeDocument;
import com.catalogo.filmes.dto.FilmeRequest;
import com.catalogo.filmes.dto.FilmeResponse;
import com.catalogo.filmes.service.FilmeSearchService;
import com.catalogo.filmes.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    private final FilmeService filmeService;
    private final FilmeSearchService filmeSearchService;


    public FilmeController(FilmeService filmeService, FilmeSearchService filmeSearchService) {
        this.filmeService = filmeService;
        this.filmeSearchService = filmeSearchService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FilmeResponse> salvar(
            @RequestBody @Valid FilmeRequest dto) {

        FilmeResponse salvo = filmeService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FilmeResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid FilmeRequest dto) {

        FilmeResponse filme = filmeService.atualizar(id, dto);
        return ResponseEntity.ok(filme);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> buscarPorId(@PathVariable Long id) {
        FilmeResponse filme = filmeService.buscarPorId(id);
        return ResponseEntity.ok(filme);
    }

    @GetMapping
    public ResponseEntity<Page<FilmeResponse>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(filmeService.getAllPaginado(page, size));
    }


    @GetMapping("/search")
    public List<FilmeDocument> search(@RequestParam String query) throws IOException {
        return filmeSearchService.buscar(query);
    }
}