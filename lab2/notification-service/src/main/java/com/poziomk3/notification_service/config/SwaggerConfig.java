package com.poziomk3.notification_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPIFromYaml() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("openapi.yaml");
        SwaggerParseResult parseResult = new OpenAPIV3Parser().readLocation("src/main/resources/openapi.yaml", null, null);
        return parseResult.getOpenAPI();
    }
}
