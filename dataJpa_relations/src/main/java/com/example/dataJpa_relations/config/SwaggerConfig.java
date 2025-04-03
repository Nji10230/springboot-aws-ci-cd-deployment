package com.example.dataJpa_relations.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI productApiDocs() {
        return new OpenAPI()
                .openapi("3.0.1") // ✅ Explicitly specify OpenAPI version
                .info(new Info()
                        .title("Product Management API")
                        .version("1.0.0") // ✅ Correct format (no 'v' prefix)
                        .description("RESTful API for managing products in the inventory system")
                        .contact(new Contact()
                                .name("API Support Team")
                                .email("apisupport@company.com"))
                        .license(new License()
                                .name("Company License")
                                .url("https://www.company.com/licenses")));
    }
}
