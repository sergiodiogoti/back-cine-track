package com.catalogo.filmes.infra.elastic.document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Document(indexName = "filmes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String titulo;

    @Field(type = FieldType.Text)
    private String genero;

    @Field(type = FieldType.Text)
    private String sinopse;

    @Field(type = FieldType.Integer)
    private Integer ano;

    @Field(type = FieldType.Double)
    private BigDecimal nota;
}

