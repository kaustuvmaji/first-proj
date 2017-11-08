package com.example.demo.app.configuration;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VelocityEngineConfigurer {

	
	@Bean
	VelocityEngine velocityEngine() {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty("resource.loader", "class");
		ve.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		return ve;
	}
}
