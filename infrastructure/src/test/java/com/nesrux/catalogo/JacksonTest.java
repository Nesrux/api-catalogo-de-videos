package com.nesrux.catalogo;

import org.junit.jupiter.api.Tag;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test-integration")
@Tag("integrationTest")
public @interface JacksonTest {
}