package com.example.demo;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableOAuth2Sso
public class DemoApplication {

	private static final Logger LOG = Logger.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		if (LOG.isDebugEnabled()) {
			LOG.debug("com.kaustuv.spring.example.boot.rest started at " + LocalDateTime.now());
		}
	}
}
