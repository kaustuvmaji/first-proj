package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	// @Bean
	// public Docket api() {
	// return new Docket(DocumentationType.SWAGGER_2)
	// .select()
	// .apis(RequestHandlerSelectors.any())
	// .paths(PathSelectors.any())
	// .build();
	// }

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo"))
				.paths(PathSelectors.ant("/employee/services/*")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Spring Boot Rest App Example", "Dev : kaustuv", "1.0.0", "",
				"kaustuv.maji@gmail.com", "http://kaustuvmaji.blogspot.in/", "http://kaustuvmaji.blogspot.in/");
		return apiInfo;
	}
}
