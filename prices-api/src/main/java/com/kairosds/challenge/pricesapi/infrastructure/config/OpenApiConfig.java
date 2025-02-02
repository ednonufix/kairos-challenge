package com.kairosds.challenge.pricesapi.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		var openapi = new OpenAPI().info(new Info().title("Kairós Challenge API").version("1.0.0"))
			.addSecurityItem(new SecurityRequirement().addList("bearer-jwt"));
		openapi.setComponents(new Components().addSecuritySchemes("bearer-jwt", new SecurityScheme()
			.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
		return openapi;
	}

}
