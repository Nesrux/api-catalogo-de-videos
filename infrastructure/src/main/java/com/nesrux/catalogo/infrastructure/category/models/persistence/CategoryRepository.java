package com.nesrux.catalogo.infrastructure.category.models.persistence;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryRepository extends ElasticsearchRepository<CategoryDocoument, String> {}
