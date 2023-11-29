package com.javappa.start.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi byIdApi() {
        return GroupedOpenApi.builder()
                .group("byId")
                .pathsToMatch("/api/{entity}/{id}")
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title("Sporting Events Web")
                        .description("Sporting Events Web is a web application that allows users to browse and manage sports events")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}