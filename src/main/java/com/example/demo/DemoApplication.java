package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * This class is responsible to redirect user to swagger ui so it will be easy
	 * for integrator or reader to understand what type of apis are published by
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

	// @Bean
	// public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
	// return new RestAppSecurity();
	// }

}
