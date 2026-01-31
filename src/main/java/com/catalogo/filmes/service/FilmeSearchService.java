package com.catalogo.filmes.service;

import com.catalogo.filmes.infra.elastic.document.FilmeDocument;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeSearchService {

    private final ElasticsearchOperations operations;

    public FilmeSearchService(ElasticsearchOperations operations) {
        this.operations = operations;
    }

    public List<FilmeDocument> buscar(String query) {

        if (query == null || query.trim().length() < 3) {
            return List.of();
        }

        Criteria criteria = new Criteria("titulo").matches(query)
                .or(new Criteria("genero").matches(query))
                .or(new Criteria("sinopse").matches(query));

        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<FilmeDocument> hits =
                operations.search(searchQuery, FilmeDocument.class);

        return hits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}

