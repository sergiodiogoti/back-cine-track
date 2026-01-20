package com.catalogo.filmes.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.catalogo.filmes.search.FilmeDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FilmeSearchService {

    private static final String INDEX = "filmes";

    private final ElasticsearchClient esClient;

    public List<FilmeDoc> buscarPorTexto(String texto, int page, int size) throws IOException {
        int safePage = Math.max(0, page);
        int safeSize = Math.max(1, size);
        int from = safePage * safeSize;

        SearchResponse<FilmeDoc> response =
                esClient.search(s -> s
                                .index(INDEX)
                                .from(from)
                                .size(safeSize)
                                .trackScores(true)
                                .query(q -> q
                                        .multiMatch(m -> m
                                                .query(texto)
                                                .fields(
                                                        "titulo^3",
                                                        "genero",
                                                        "sinopse^2"
                                                )
                                                .fuzziness("AUTO")
                                        )
                                )
                                .highlight(h -> h
                                        .preTags("<mark>")
                                        .postTags("</mark>")
                                        .fields("sinopse", hf -> hf)
                                )
                                .sort(so -> so
                                        .score(sc -> sc.order(SortOrder.Desc))
                                ),
                        FilmeDoc.class
                );

        List<FilmeDoc> docs = new ArrayList<>();

        for (Hit<FilmeDoc> hit : response.hits().hits()) {
            FilmeDoc doc = hit.source();
            if (doc == null) continue;

            doc.setScore(hit.score());

            Map<String, List<String>> highlight = hit.highlight();
            if (highlight != null && highlight.containsKey("sinopse")) {
                doc.setSinopseFormatada(highlight.get("sinopse").get(0));
            }

            docs.add(doc);
        }

        return docs;
    }
}

