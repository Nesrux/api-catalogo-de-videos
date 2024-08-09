package com.nesrux.catalogo.infrastructure.category.list;

import com.nesrux.catalogo.infrastructure.UseCase;
import com.nesrux.catalogo.domain.category.CategoryGateway;
import com.nesrux.catalogo.domain.category.CategorySearchQuery;
import com.nesrux.catalogo.domain.pagination.Pagination;

import java.util.Objects;

public class ListCategoryUseCase extends UseCase<CategorySearchQuery, Pagination<ListCategoryOutput>> {
    private final CategoryGateway categoryGateway;

    public ListCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<ListCategoryOutput> execute(CategorySearchQuery aQuerry) {
        return this.categoryGateway.findAll(aQuerry)
                .map(ListCategoryOutput::from);
    }
}
