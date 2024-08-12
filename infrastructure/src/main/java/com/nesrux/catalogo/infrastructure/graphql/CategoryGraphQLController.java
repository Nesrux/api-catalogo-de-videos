package com.nesrux.catalogo.infrastructure.graphql;


import com.nesrux.catalogo.domain.category.Category;
import com.nesrux.catalogo.domain.category.CategorySearchQuery;
import com.nesrux.catalogo.infrastructure.category.list.ListCategoryOutput;
import com.nesrux.catalogo.infrastructure.category.list.ListCategoryUseCase;
import com.nesrux.catalogo.infrastructure.category.models.CategoryDto;
import com.nesrux.catalogo.infrastructure.category.save.SaveCategoryUseCase;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
public class CategoryGraphQLController {

    private final ListCategoryUseCase listCategoryUseCase;
    private final SaveCategoryUseCase saveCategoryUseCase;

    public CategoryGraphQLController(
            final ListCategoryUseCase listCategoryUseCase,
            final SaveCategoryUseCase saveCategoryUseCase
    ) {
        this.listCategoryUseCase = Objects.requireNonNull(listCategoryUseCase);
        this.saveCategoryUseCase = Objects.requireNonNull(saveCategoryUseCase);
    }

    @QueryMapping
    public List<ListCategoryOutput> categories(
            @Argument final String search,
            @Argument final int page,
            @Argument final int perPage,
            @Argument final String sort,
            @Argument final String direction
    ) {

        final var aQuery =
                new CategorySearchQuery(page, perPage, search, sort, direction);

        return this.listCategoryUseCase.execute(aQuery).data();
    }

    @MutationMapping
    public Category saveCategory(@Argument final CategoryDto input) {
        return this.saveCategoryUseCase.execute(input.toCategory());
    }

}