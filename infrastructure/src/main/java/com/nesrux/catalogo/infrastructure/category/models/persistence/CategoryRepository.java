package com.nesrux.catalogo.infrastructure.category.models.persistence;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ElasticsearchRepository<CategoryDocoument, String> {}
