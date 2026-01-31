package com.catalogo.filmes.infra.elastic;

import com.catalogo.filmes.infra.elastic.document.FilmeDocument;
import com.catalogo.filmes.mapper.FilmeMapper;
import com.catalogo.filmes.model.Filme;
import com.catalogo.filmes.repository.FilmeElasticRepository;
import com.catalogo.filmes.repository.FilmeRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class FilmeElasticStartupIndexer {

    private final FilmeRepository filmeRepository;
    private final FilmeElasticRepository elasticRepository;
    private final FilmeMapper mapper;

    public FilmeElasticStartupIndexer(FilmeRepository filmeRepository, FilmeElasticRepository elasticRepository, FilmeMapper mapper) {
        this.filmeRepository = filmeRepository;
        this.elasticRepository = elasticRepository;
        this.mapper = mapper;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void indexarFilmesNoStartup() {

            int tentativas = 0;

            while (tentativas < 5) {
                try {
                    if (elasticRepository.count() > 0) {
                        return;
                    }

                    List<Filme> filmes = filmeRepository.findAll();

                    List<FilmeDocument> documentos = filmes.stream()
                            .map(mapper::toDocument)
                            .toList();

                    elasticRepository.saveAll(documentos);

                    System.out.println("Elasticsearch indexado com sucesso");
                    return;

                } catch (Exception e) {
                    tentativas++;
                    System.out.println("MySQL ainda não disponível, tentando novamente...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ignored) {}
                }
            }

            System.out.println("Falha ao indexar filmes no startup");
    }
}

