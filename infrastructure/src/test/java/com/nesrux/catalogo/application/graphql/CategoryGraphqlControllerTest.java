package com.nesrux.catalogo.application.graphql;

import com.nesrux.catalogo.application.GraphQlControllerTest;
import com.nesrux.catalogo.application.category.list.ListCategoryOutput;
import com.nesrux.catalogo.application.category.list.ListCategoryUseCase;
import com.nesrux.catalogo.domain.Fixture;
import com.nesrux.catalogo.domain.pagination.Pagination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@GraphQlControllerTest
public class CategoryGraphqlControllerTest {
    @MockBean
    private ListCategoryUseCase useCase;

    @Autowired
    private GraphQlTester graphql;


    @Test
    public void testListCategories() {
        //given
        final var expectedCategories = List.of(ListCategoryOutput.from(Fixture.Categories.aulas()), ListCategoryOutput.from(Fixture.Categories.lives()));

        final var expectedPage = 3;
        final var expectedPerPage = 10;
        final var expectedSort = "name";
        final var expectedDirection = "asc";

        when(this.useCase.execute(any())).thenReturn(new Pagination<>(expectedPage, expectedPage, expectedCategories.size(), expectedCategories));

        final var query = """
                {
                 categories {
                   id
                   name
                  }
                }
                """;
        //when
        final var res = this.graphql.document(query).execute();
        final var actualCategories = res.path("categories")
                .entityList(ListCategoryOutput.class)
                .get();
        //then
        Assertions.assertTrue(actualCategories.size() == expectedCategories.size()
                && actualCategories.containsAll(expectedCategories));

    }
}
