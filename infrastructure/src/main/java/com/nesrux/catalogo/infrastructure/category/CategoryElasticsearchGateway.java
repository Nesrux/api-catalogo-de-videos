package com.nesrux.catalogo.infrastructure.category;

import com.nesrux.catalogo.domain.category.Category;
import com.nesrux.catalogo.domain.category.CategoryGateway;
import com.nesrux.catalogo.domain.category.CategorySearchQuery;
import com.nesrux.catalogo.domain.pagination.Pagination;
import com.nesrux.catalogo.infrastructure.category.models.persistence.CategoryDocoument;
import com.nesrux.catalogo.infrastructure.category.models.persistence.CategoryRepository;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.elasticsearch.core.SearchOperations;
import org.springframework.stereotype.Component;
import org.xnio.channels.UnsupportedOptionException;

@Component
public class CategoryElasticsearchGateway implements CategoryGateway {

   private final CategoryRepository repository;
   private final SearchOperations searchOperations;

   public CategoryElasticsearchGateway(
      final CategoryRepository repository,
      final SearchOperations searchOperations
   ) {
      this.repository = Objects.requireNonNull(repository);
      this.searchOperations = Objects.requireNonNull(searchOperations);
   }

   @Override
   public Category save(final Category aCategory) {
      this.repository.save(CategoryDocoument.from(aCategory));
      return aCategory;
   }

   @Override
   public void deleteById(final String anId) {
      throw new UnsupportedOptionException();
   }

   @Override
   public Optional<Category> findById(String anId) {
      throw new UnsupportedOptionException();
   }

   @Override
   public Pagination<Category> findAll(CategorySearchQuery aQuery) {
      throw new UnsupportedOptionException();
   }
}
