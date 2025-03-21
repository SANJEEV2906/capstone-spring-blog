package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Entry point for the Spring Boot Blog Service application.
 * 
 *
 * 
 * This application provides a RESTful API for managing blogs and comments.
 * 
 * API Documentation:
 * - Swagger UI: Accessible at /swagger-ui.html (if enabled)
 */

//Improve API Documentation with Swagger

@OpenAPIDefinition(info = @Info(title = "Blog API", version = "1.0", description = "Spring Boot REST API for Blog Management"))
//OpenAPI is a specification for describing REST API interfaces

@SpringBootApplication
public class SpringBootBlogServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogServiceApplication.class, args);
	}

}
