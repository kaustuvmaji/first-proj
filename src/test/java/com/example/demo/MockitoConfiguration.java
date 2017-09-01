package com.example.demo;

import org.mockito.Mockito;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@EnableCaching
public class MockitoConfiguration {

	public MockitoConfiguration() {
	}

	@Bean
	// prioritizing
	@Primary
	EmployeeService employeeService() {
		return Mockito.mock(EmployeeServiceImpl.class);
	}
}
