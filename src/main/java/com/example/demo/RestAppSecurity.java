package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class RestAppSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("kaustuv").password("pass@123").roles("USER").and().withUser("admin")
				.password("admin").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/services/**").authenticated();
		// http.csrf().disable();
		// http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		// http.formLogin().successHandler(authenticationSuccessHandler);
		// http.formLogin().failureHandler(authenticationFailureHandler);
		// super.configure(auth);
	}
}
