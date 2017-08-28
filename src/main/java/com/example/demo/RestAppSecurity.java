package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class RestAppSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.inMemoryAuthentication().withUser("user").password("user").roles("ADMIN").and().withUser("admin")
				.password("admin").roles("ADMIN");
		builder.eraseCredentials(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// http.authorizeRequests().antMatchers("/employee/services/**").authenticated();
		http.csrf().disable();
		// http.authorizeRequests().antMatchers("/employee/services/addEmployee").hasAnyRole("ADMIN").and().authorizeRequests();
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		// http.formLogin().successHandler(authenticationSuccessHandler);
		// http.formLogin().failureHandler(authenticationFailureHandler);
		// super.configure(http);
	}
}
