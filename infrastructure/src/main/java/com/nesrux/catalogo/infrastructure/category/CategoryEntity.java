package com.nesrux.catalogo.infrastructure.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "categories")
public class CategoryEntity {

    @Id
    private String id;
}
