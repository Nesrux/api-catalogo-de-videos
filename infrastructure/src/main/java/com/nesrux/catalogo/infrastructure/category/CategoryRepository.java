package com.nesrux.catalogo.infrastructure.category;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryRepository extends ElasticsearchRepository<CategoryEntity, String> {}
