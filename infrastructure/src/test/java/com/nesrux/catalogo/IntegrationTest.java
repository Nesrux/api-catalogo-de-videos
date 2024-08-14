package com.nesrux.catalogo;

import com.nesrux.catalogo.infrastructure.configuration.WebServerConfig;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test-integration")
@EnableAutoConfiguration(exclude = { ElasticsearchRepositoriesAutoConfiguration.class })
@SpringBootTest(classes = { WebServerConfig.class, IntegrationTestConfiguration.class })
@Tag("integrationTest")
public @interface IntegrationTest {
}
