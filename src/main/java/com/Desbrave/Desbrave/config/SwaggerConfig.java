package com.Desbrave.Desbrave.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configurable
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
        .info(new Info()
                    .title("Desbrave API")
                    .version("1.0")
                    .description("Documentação da API do projeto Desbrave"))
            . addSecurityItem(new SecurityRequirement().addList("x-auth"))
            .components(new io.swagger.v3.oas.models.Components()
            .addSecuritySchemes("x-auth", new SecurityScheme()
            .name("x-auth")
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER))

            );
    }

}
