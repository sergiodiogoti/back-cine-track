package com.catalogo.filmes.controller;

import com.catalogo.filmes.dto.FilmeRequest;
import com.catalogo.filmes.dto.FilmeResponse;
import com.catalogo.filmes.dto.PageResponse;
import com.catalogo.filmes.payload.CriteriaRequest;
import com.catalogo.filmes.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FilmeResponse> salvar(
            @RequestBody @Valid FilmeRequest dto) {

        FilmeResponse salvo = filmeService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> buscarPorId(@PathVariable Long id) {
        FilmeResponse filme = filmeService.buscarPorId(id);
        return ResponseEntity.ok(filme);
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

    @GetMapping
    public ResponseEntity<Page<FilmeResponse>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(filmeService.getAllPaginado(page, size));
    }


//    @GetMapping("/search")
//    public ResponseEntity<List<FilmeResponse>> search(
//            @RequestParam(value = "titulo", required = false) Optional<String> titulo,
//            @RequestParam(value = "genero", required = false) Optional<String> genero) {
//
//        CriteriaRequest criteriaRequest =
//                new CriteriaRequest(titulo, genero);
//
//        List<FilmeResponse> filmes =
//                filmeService.search(criteriaRequest);
//
//        return ResponseEntity.ok(filmes);
//    }
}
