package com.nesrux.catalogo.application.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nesrux.catalogo.application.infrastructure.configuration.json.Json;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper mapper(){
        return Json.mapper();
    }
}
