package com.nesrux.catalogo.infrastructure.category;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.nesrux.catalogo.infrastructure.category.models.persistence.CategoryDocoument;

public interface CategoryRepository extends ElasticsearchRepository<CategoryDocoument, String> {}
