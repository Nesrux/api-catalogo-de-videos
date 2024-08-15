package com.nesrux.catalogo;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test-integration")
@ComponentScan(
   basePackages = "com.nesrux.catalogo",
   useDefaultFilters = false,
   includeFilters = {
      @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*ElasticsearchGateway"),
   }
)
@DataElasticsearchTest
@ImportTestcontainers(ElasticSearchTestContainer.class)
@Tag("integrationTest")
@Testcontainers
public abstract class AbstractElasticSearchTest {

   @Autowired
   private Collection<ElasticsearchRepository<?, ?>> repositories;

   @BeforeEach
   void cleanUp() {
      this.repositories.forEach(ElasticsearchRepository::deleteAll);
   }
}
