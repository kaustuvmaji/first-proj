package com.example.demo.restinterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo")).paths(regex("/employee.*")).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Spring Boot Rest App Example",
				"This is an spring boot example rest app. secruity credential kaustuv/pass@123. Click list Operations tab to explore operations. "
						+ "Enabled eh-cahce for read services."
						+ " Used AOP @around to evaluate the each method execution time this will be useful "
						+ "during performance test evaluation."
						+ "\n",
				"1.0.0", "free for learning no terms",
				new springfox.documentation.service.Contact("Kaustuv Maji", "http://kaustuvmaji.blogspot.in/",
						"kaustuv.maji@gmail.com"),
				"http://kaustuvmaji.blogspot.in/", "http://kaustuvmaji.blogspot.in/");

		return apiInfo;
	}
}
