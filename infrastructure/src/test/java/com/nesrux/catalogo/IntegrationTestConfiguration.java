package com.nesrux.catalogo;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

import com.nesrux.catalogo.infrastructure.category.models.persistence.CategoryRepository;

public class IntegrationTestConfiguration {

  @Bean
  public CategoryRepository categoryRepository() {
    return Mockito.mock(CategoryRepository.class);
  }
}
