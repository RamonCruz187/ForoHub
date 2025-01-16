package com.alura.forohub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Documentation").version("1.0"))
                .addServersItem(new Server().url("https://efficient-abundance-production.up.railway.app/"));
    }
}
