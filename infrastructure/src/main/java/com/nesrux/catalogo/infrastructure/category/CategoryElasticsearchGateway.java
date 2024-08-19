package com.nesrux.catalogo.infrastructure.category;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.xnio.channels.UnsupportedOptionException;

import com.nesrux.catalogo.domain.category.Category;
import com.nesrux.catalogo.domain.category.CategoryGateway;
import com.nesrux.catalogo.domain.category.CategorySearchQuery;
import com.nesrux.catalogo.domain.pagination.Pagination;
import com.nesrux.catalogo.infrastructure.category.models.persistence.CategoryDocoument;
import com.nesrux.catalogo.infrastructure.category.models.persistence.CategoryRepository;

@Component
public class CategoryElasticsearchGateway implements CategoryGateway {

   private final CategoryRepository repository;

   public CategoryElasticsearchGateway(final CategoryRepository repository) {
      this.repository = Objects.requireNonNull(repository);
   }

   @Override
   public Category save(final Category aCategory) {
      this.repository.save(CategoryDocoument.from(aCategory));
      return aCategory;
   }

   @Override
   public void deleteById(final String anId) {
      if (repository.existsById(anId)) {
         repository.deleteById(anId);
      }
   }

   @Override
   public Optional<Category> findById(final String anId) {
      return this.repository.findById(anId).map(CategoryDocoument::toCategory);
   }

   @Override
   public Pagination<Category> findAll(CategorySearchQuery aQuery) {
      throw new UnsupportedOptionException();
   }
}
