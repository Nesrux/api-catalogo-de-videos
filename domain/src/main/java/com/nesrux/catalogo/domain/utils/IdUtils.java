package com.nesrux.catalogo.domain.utils;

import java.util.UUID;

public final class IdUtils {
    private IdUtils() {
    }

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
