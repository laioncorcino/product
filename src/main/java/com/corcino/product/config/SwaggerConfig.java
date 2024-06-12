package com.corcino.product.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenApi() {

        return new OpenAPI()
            .components(new Components())
            .info(new Info()
                    .title("ProductsApi - Controle de produtos")
                    .description("API Product")
                    .version("v1")
            );
    }

}
