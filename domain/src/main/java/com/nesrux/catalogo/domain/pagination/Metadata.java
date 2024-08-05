package com.nesrux.catalogo.domain.pagination;

public record Metadata(
        int currentPage,
        int perPage,
        long total
) {
}