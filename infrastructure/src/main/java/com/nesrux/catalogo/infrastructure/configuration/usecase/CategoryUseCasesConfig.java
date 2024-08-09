package com.nesrux.catalogo.infrastructure.configuration.usecase;

import com.nesrux.catalogo.infrastructure.category.delete.DeleteCategoryUseCase;
import com.nesrux.catalogo.infrastructure.category.list.ListCategoryUseCase;
import com.nesrux.catalogo.infrastructure.category.save.SaveCategoryUseCase;
import com.nesrux.catalogo.domain.category.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CategoryUseCasesConfig {
    private final CategoryGateway categoryGateway;

    public CategoryUseCasesConfig(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DeleteCategoryUseCase(categoryGateway);
    }

    @Bean
    public SaveCategoryUseCase useCase() {
        return new SaveCategoryUseCase(categoryGateway);
    }

    @Bean
    public ListCategoryUseCase listCategoryUseCase() {
        return new ListCategoryUseCase(categoryGateway);
    }
}
