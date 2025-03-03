package com.nesrux.catalogo.infrastructure.application.category.list;

import com.nesrux.catalogo.infrastructure.application.UseCaseTest;
import com.nesrux.catalogo.infrastructure.category.list.ListCategoryOutput;
import com.nesrux.catalogo.infrastructure.category.list.ListCategoryUseCase;
import com.nesrux.catalogo.domain.Fixture;
import com.nesrux.catalogo.domain.category.CategoryGateway;
import com.nesrux.catalogo.domain.category.CategorySearchQuery;
import com.nesrux.catalogo.domain.pagination.Pagination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ListCategoryUseCaseTest extends UseCaseTest {
    @InjectMocks
    private ListCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    public void givenValidQuery_whenCallsListCategories_shouldReturnCategories() {
        //given
        final var categories = List.of(
                Fixture.Categories.aulas(),
                Fixture.Categories.lives()
        );
        final var expectedItems = categories.stream().map(ListCategoryOutput::from)
                .toList();
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "Algo";
        final var expectedSort = "name";
        final var expectedDirection = "asc";
        final var expectedItemsCount = 2;

        final var aQuery =
                new CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var pagination =
                new Pagination<>(expectedPage, expectedPerPage, categories.size(), categories);

        when(this.categoryGateway.findAll(any()))
                .thenReturn(pagination);

        //when
        final var actualOutput = this.useCase.execute(aQuery);

        //then
        Assertions.assertEquals(expectedPage, actualOutput.meta().currentPage());
        Assertions.assertEquals(expectedPerPage, actualOutput.meta().perPage());
        Assertions.assertEquals(expectedItemsCount, actualOutput.meta().total());
        Assertions.assertTrue(expectedItems.size() == actualOutput.data().size()&&
                expectedItems.containsAll(actualOutput.data()));
    }
}
