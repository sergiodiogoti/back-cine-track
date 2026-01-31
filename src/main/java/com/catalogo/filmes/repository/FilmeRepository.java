package com.catalogo.filmes.repository;


import com.catalogo.filmes.model.Filme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    @Query("""
        SELECT f FROM Filme f
        WHERE LOWER(f.titulo) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(f.genero) LIKE LOWER(CONCAT('%', :texto, '%'))
    """)
    Page<Filme> searchByTexto(
            @Param("texto") String texto,
            Pageable pageable
    );
}
