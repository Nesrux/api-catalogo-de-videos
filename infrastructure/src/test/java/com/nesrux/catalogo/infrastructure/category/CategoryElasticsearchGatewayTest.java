package com.nesrux.catalogo.infrastructure.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nesrux.catalogo.AbstractElasticSearchTest;
import com.nesrux.catalogo.domain.Fixture;
import com.nesrux.catalogo.domain.utils.IdUtils;
import com.nesrux.catalogo.infrastructure.category.models.persistence.CategoryDocoument;
import com.nesrux.catalogo.infrastructure.category.models.persistence.CategoryRepository;

public class CategoryElasticsearchGatewayTest extends AbstractElasticSearchTest {

   @Autowired
   private CategoryElasticsearchGateway categoryGateway;

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
      Assertions.assertEquals(aulas.id(), actualCategory.id());
      Assertions.assertEquals(aulas.name(), actualCategory.name());
      Assertions.assertEquals(aulas.description(), actualCategory.description());
      Assertions.assertEquals(aulas.active(), actualCategory.active());
      Assertions.assertEquals(aulas.createdAt(), actualCategory.createdAt());
      Assertions.assertEquals(aulas.updatedAt(), actualCategory.updatedAt());
      Assertions.assertEquals(aulas.deletedAt(), actualCategory.deletedAt());
   }

   @Test
   public void givenAvalidId_whenCallsDeleteById_shouldBeOk() {
      //given
      final var aCategory = Fixture.Categories.aulas();
      final var expectedId = aCategory.id();
      final var expectedCount = 0;

      categoryRepository.save(CategoryDocoument.from(aCategory));
      Assertions.assertTrue(categoryRepository.existsById(expectedId));

      //when
      Assertions.assertDoesNotThrow(() -> categoryGateway.deleteById(expectedId));
      //then
      Assertions.assertEquals(expectedCount, categoryRepository.count());
   }

   @Test
   public void givenAnIvalidId_whenCallsDeleteById_shouldBeOk() {
      //given
      final var aCategory = Fixture.Categories.aulas();
      final var expectedId = IdUtils.randomUUID();
      final var expectedCount = 1;

      categoryRepository.save(CategoryDocoument.from(aCategory));
      Assertions.assertTrue(categoryRepository.existsById(expectedId));

      //when
      Assertions.assertDoesNotThrow(() -> categoryGateway.deleteById(expectedId));
      //then
      Assertions.assertEquals(expectedCount, categoryRepository.count());
   }
}
