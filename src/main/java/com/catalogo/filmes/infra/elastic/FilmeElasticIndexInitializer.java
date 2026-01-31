package com.catalogo.filmes.infra.elastic;

import com.catalogo.filmes.infra.elastic.document.FilmeDocument;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class FilmeElasticIndexInitializer {

    private final ElasticsearchOperations operations;

    public FilmeElasticIndexInitializer(ElasticsearchOperations operations) {
        this.operations = operations;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void recreateIndex() {

        IndexOperations indexOps = operations.indexOps(FilmeDocument.class);

        if (indexOps.exists()) {
            indexOps.delete();
        }

        indexOps.create();
        indexOps.putMapping(indexOps.createMapping());

        System.out.println("Índice Elasticsearch recriado com mapeamento correto");
    }
}

