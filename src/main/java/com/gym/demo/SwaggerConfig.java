package com.gym.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Localized Fitness Gym Finder API")
                        .version("1.0")
                        .description("API documentation for the Localized Fitness Gym Finder application")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
