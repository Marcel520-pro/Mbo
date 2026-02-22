package org.logonedigital.mboa_care;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Teleconsultation")
                        .description("API pour gérer les profils Utilisateur ")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Jerry Floyd")
                                .email("jerryfeudjio@gmail.com")
                                .url("https://logonedigital.org"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation complète")
                        .url("https://logonedigital.org/docs"));
    }
}