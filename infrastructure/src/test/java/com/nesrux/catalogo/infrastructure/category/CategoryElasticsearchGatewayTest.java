package com.nesrux.catalogo.infrastructure.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.org.apache.commons.lang3.StringUtils;

import com.nesrux.catalogo.AbstractElasticSearchTest;
import com.nesrux.catalogo.domain.Fixture;
import com.nesrux.catalogo.domain.category.CategorySearchQuery;
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
      Assertions.assertTrue(categoryRepository.existsById(aCategory.id()));

      //when
      Assertions.assertDoesNotThrow(() -> categoryGateway.deleteById(expectedId));
      //then
      Assertions.assertEquals(expectedCount, categoryRepository.count());
   }

   @Test
   public void givenAValidId_whenCallsfindById_shouldRetrivedIt() {
      //given
      final var aVideo = Fixture.Categories.aulas();
      final var anId = aVideo.id();

      categoryRepository.save(CategoryDocoument.from(aVideo));
      //when
      final var actualOutput = categoryGateway.findById(anId).get();

      //then
      Assertions.assertNotNull(actualOutput);
      Assertions.assertNotNull(actualOutput.id());
      Assertions.assertEquals(aVideo.name(), actualOutput.name());
      Assertions.assertEquals(aVideo.description(), actualOutput.description());
      Assertions.assertEquals(aVideo.active(), actualOutput.active());
      Assertions.assertEquals(aVideo.createdAt(), actualOutput.createdAt());
      Assertions.assertEquals(aVideo.updatedAt(), actualOutput.updatedAt());
      Assertions.assertEquals(aVideo.deletedAt(), actualOutput.deletedAt());
   }

   @Test
   public void givenAnInvalidId_whenCallsFindById_shouldReturnEmpty() {
      //given
      final var aCategory = Fixture.Categories.lives();
      final var anInvalidId = IdUtils.randomUUID();

      categoryRepository.save(CategoryDocoument.from(aCategory));
      //when
      final var actualOutput = this.categoryGateway.findById(anInvalidId);

      //then
      Assertions.assertTrue(actualOutput.isEmpty());
   }

     @Test
    public void givenEmptyCategories_whenCallsFindAll_shouldReturnEmptyList() {
        // given
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "name";
        final var expectedDirection = "asc";
        final var expectedTotal = 0;

        final var aQuery =
                new CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        // when
        final var actualOutput = this.categoryGateway.findAll(aQuery);

        // then
        Assertions.assertEquals(expectedPage, actualOutput.meta().currentPage());
        Assertions.assertEquals(expectedPerPage, actualOutput.meta().perPage());
        Assertions.assertEquals(expectedTotal, actualOutput.meta().total());
        Assertions.assertEquals(expectedTotal, actualOutput.data().size());
    }

    @ParameterizedTest
    @CsvSource({
            "aul,0,10,1,1,Aulas",
            "liv,0,10,1,1,Lives"
    })
    public void givenValidTerm_whenCallsFindAll_shouldReturnElementsFiltered(
            final String expectedTerms,
            final int expectedPage,
            final int expectedPerPage,
            final int expectedItemsCount,
            final long expectedTotal,
            final String expectedName
    ) {
        // given
        mockCategories();

        final var expectedSort = "name";
        final var expectedDirection = "asc";

        final var aQuery =
                new CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        // when
        final var actualOutput = this.categoryGateway.findAll(aQuery);

        // then
        Assertions.assertEquals(expectedPage, actualOutput.meta().currentPage());
        Assertions.assertEquals(expectedPerPage, actualOutput.meta().perPage());
        Assertions.assertEquals(expectedTotal, actualOutput.meta().total());
        Assertions.assertEquals(expectedItemsCount, actualOutput.data().size());
        Assertions.assertEquals(expectedName, actualOutput.data().get(0).name());
    }

    @ParameterizedTest
    @CsvSource({
            "name,asc,0,10,3,3,Aulas",
            "name,desc,0,10,3,3,Talks",
            "created_at,asc,0,10,3,3,Aulas",
            "created_at,desc,0,10,3,3,Lives",
    })
    public void givenValidSortAndDirection_whenCallsFindAll_shouldReturnElementsSorted(
            final String expectedSort,
            final String expectedDirection,
            final int expectedPage,
            final int expectedPerPage,
            final int expectedItemsCount,
            final long expectedTotal,
            final String expectedName
    ) {
        // given
        mockCategories();

        final var expectedTerms = "";

        final var aQuery =
                new CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        // when
        final var actualOutput = this.categoryGateway.findAll(aQuery);

        // then
        Assertions.assertEquals(expectedPage, actualOutput.meta().currentPage());
        Assertions.assertEquals(expectedPerPage, actualOutput.meta().perPage());
        Assertions.assertEquals(expectedTotal, actualOutput.meta().total());
        Assertions.assertEquals(expectedItemsCount, actualOutput.data().size());
        Assertions.assertEquals(expectedName, actualOutput.data().get(0).name());
    }

    @ParameterizedTest
    @CsvSource({
            "0,1,1,3,Aulas",
            "1,1,1,3,Lives",
            "2,1,1,3,Talks",
            "3,1,0,3,",
    })
    public void givenValidPage_whenCallsFindAll_shouldReturnElementsPaged(
            final int expectedPage,
            final int expectedPerPage,
            final int expectedItemsCount,
            final long expectedTotal,
            final String expectedName
    ) {
        // given
        mockCategories();

        final var expectedTerms = "";
        final var expectedSort = "name";
        final var expectedDirection = "asc";

        final var aQuery =
                new CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        // when
        final var actualOutput = this.categoryGateway.findAll(aQuery);

        // then
        Assertions.assertEquals(expectedPage, actualOutput.meta().currentPage());
        Assertions.assertEquals(expectedPerPage, actualOutput.meta().perPage());
        Assertions.assertEquals(expectedTotal, actualOutput.meta().total());
        Assertions.assertEquals(expectedItemsCount, actualOutput.data().size());

        if (StringUtils.isNotEmpty(expectedName)) {
            Assertions.assertEquals(expectedName, actualOutput.data().get(0).name());
        }
    }

    private void mockCategories() {
        this.categoryRepository.save(CategoryDocoument.from(Fixture.Categories.aulas()));
        this.categoryRepository.save(CategoryDocoument.from(Fixture.Categories.talks()));
        this.categoryRepository.save(CategoryDocoument.from(Fixture.Categories.lives()));
    }
}
