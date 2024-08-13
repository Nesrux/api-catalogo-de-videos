package com.nesrux.catalogo.infrastructure.category.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nesrux.catalogo.domain.category.Category;

public record CategoryDto(
    @JsonProperty("id") String id,
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("is_active") Boolean active,
    @JsonProperty("created_at") Instant createdAt,
    @JsonProperty("updated_at") Instant updatedAt,
    @JsonProperty("deleted_at") Instant deletedAt
) {
    @Override
    public Boolean active() {
        return active != null ? active : true;
    }

    public Category toCategory() {
        return Category.with(
            id(),
            name(),
            description(),
            active(),
            createdAt(),
            updatedAt(),
            deletedAt()
        );
    }
}
