package com.catalogo.filmes.repository;

import com.catalogo.filmes.infra.elastic.document.FilmeDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FilmeElasticRepository extends ElasticsearchRepository<FilmeDocument, Long> {
}
