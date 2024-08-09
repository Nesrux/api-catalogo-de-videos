package com.nesrux.catalogo.infrastructure.category.save;

import com.nesrux.catalogo.infrastructure.UseCase;
import com.nesrux.catalogo.domain.category.Category;
import com.nesrux.catalogo.domain.category.CategoryGateway;
import com.nesrux.catalogo.domain.exceptions.NotificationException;
import com.nesrux.catalogo.domain.validation.Error;
import com.nesrux.catalogo.domain.validation.handler.Notification;

import java.util.Objects;

public class SaveCategoryUseCase extends UseCase<Category, Category> {
    private final CategoryGateway categoryGateway;

    public SaveCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Category execute(final Category aCategory) {
        if (aCategory == null) {
            throw NotificationException.with(new Error("'aCategory' cannot be null'"));
        }
        final var notification = Notification.create();
        aCategory.validate(notification);

        if (notification.hasError()) {
            throw NotificationException.with("Invalid category", notification);
        }
        return this.categoryGateway.save(aCategory);
    }
}
