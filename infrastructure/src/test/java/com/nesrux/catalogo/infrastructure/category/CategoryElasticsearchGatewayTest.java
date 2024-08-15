package com.nesrux.catalogo.infrastructure.category;

import com.nesrux.catalogo.AbstractElasticSearchTest;
import com.nesrux.catalogo.infrastructure.category.models.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryElasticsearchGatewayTest extends AbstractElasticSearchTest {

   @Autowired
   private CategoryRepository categoryRepository;

   @Test
   public void testInjection() {
      Assertions.assertNotNull(categoryRepository);
   }
}
