package com.catalogo.filmes.controller;


import com.catalogo.filmes.search.FilmeDoc;
import com.catalogo.filmes.service.FilmeSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/filmes/search")
@RequiredArgsConstructor
public class FilmeSearchController {

    private final FilmeSearchService searchService;

    @GetMapping
    public ResponseEntity<List<FilmeDoc>> buscar(
            @RequestParam String texto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) throws IOException {

        return ResponseEntity.ok(
                searchService.buscarPorTexto(texto, page, size)
        );
    }
}

