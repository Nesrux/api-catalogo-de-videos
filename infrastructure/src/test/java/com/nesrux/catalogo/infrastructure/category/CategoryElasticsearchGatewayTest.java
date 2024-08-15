package com.nesrux.catalogo.infrastructure.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nesrux.catalogo.AbstractElasticSearchTest;
import com.nesrux.catalogo.domain.Fixture;
import com.nesrux.catalogo.infrastructure.category.models.persistence.CategoryRepository;

public class CategoryElasticsearchGatewayTest extends AbstractElasticSearchTest {

   @Autowired
   private CategoryElasticSearchGateway categoryGateway;

   @Autowired
   private CategoryRepository categoryRepository;

   @Test
   public void testInjection() {
      Assertions.assertNotNull(categoryRepository);
      Assertions.assertNotNull(categoryGateway);
   }

   @Test
   public void givenValidCategory_whenCallsSave_sholdPersistedit() {
      //given
      final var aulas = Fixture.Categories.aulas();

      //when
      final var actualOutput = categoryGateway.save(aulas);

      //then
      Assertions.assertEquals(aulas, actualOutput);

      final var actualCategory = categoryRepository.findById(actualOutput.id()).get();
      Assertions.assertEquals(aulas.id(), actualCategory.getId());
      Assertions.assertEquals(aulas.name(), actualCategory.getName());
      Assertions.assertEquals(aulas.description(), actualCategory.getDescription());
      Assertions.assertEquals(aulas.active(), actualCategory.isActive());
      Assertions.assertEquals(aulas.createdAt(), actualCategory.getCreatedAt());
      Assertions.assertEquals(aulas.updatedAt(), actualCategory.getUpdatedAt());
      Assertions.assertEquals(aulas.deletedAt(), actualCategory.getDeletedAt());
   }
}
