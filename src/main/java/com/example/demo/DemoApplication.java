package com.example.demo;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
// @ImportResource("classpath:cache-config.xml") // if we want to configure
// things in xml.
@ComponentScan(basePackages = { "com.example.demo.app.configuration",
		"com.example.demo.application", "com.example.demo.application.aop", "com.example.demo.restinterface",
		"com.example.demo.scheduler", "com.example.demo.security", "com.example.demo.app.management" })
@EnableAutoConfiguration
public class DemoApplication {

	private static final Logger LOG = Logger.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		if (LOG.isDebugEnabled()) {
			LOG.debug("com.kaustuv.spring.example.boot.rest started at " + LocalDateTime.now());
		}
	}

	/**
	 * This class is responsible to redirect user to swagger ui so it will be easy
	 * for Integrator or reader to understand what type of apis are published by
	 * this web app.
	 * 
	 * @author KMaji
	 *
	 */
	@Controller
	class WelcomeController {

		@RequestMapping(value = "/", method = RequestMethod.GET)
		String home() {
			return "redirect:/swagger-ui.html";
		}

	}
}
