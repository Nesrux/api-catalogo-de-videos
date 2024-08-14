package com.nesrux.catalogo;

import com.nesrux.catalogo.infrastructure.category.CategoryRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class IntegrationTestConfiguration {

  @Bean
  public CategoryRepository categoryRepository() {
    return Mockito.mock(CategoryRepository.class);
  }
}
