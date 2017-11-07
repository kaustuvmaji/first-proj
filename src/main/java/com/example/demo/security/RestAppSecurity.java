package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Scenario 1 : Roles based authentication using url Scenario 2 : Roles based
 * authentication using method level Annotation
 * 
 * @author KMaji
 *
 */
@Configuration
@EnableWebSecurity
/*
 * Scenario 1
 * 
 * @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
 */

/*
 * Scenario 2
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, order = SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class RestAppSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	RestAppAuthenticationProvider restAppAuthenticationProvider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * Scenario 1. using inMemoryAuthentication
		 */
		// auth.inMemoryAuthentication().withUser("admin").password("admin@123").roles("ADMIN").
		// // Admin user
		// and().withUser("user1").password("user@123").roles("USER"). // local user
		// and().withUser("kaustuv").password("pass@123").roles("USER", "ADMIN"); //
		// Myself having both

		/*
		 * Scenario 2. Example of AuthenticationProvider
		 */
		auth.authenticationProvider(restAppAuthenticationProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Scenario 1. url path configuration.
		// http.httpBasic().and().authorizeRequests().antMatchers("/employee/**").hasRole("ADMIN").antMatchers("/**")
		// .hasRole("USER").and().csrf().disable().headers().frameOptions().disable();

		// Scenario 2. this configuration is for method level configuration.
		http.httpBasic().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable().headers()
				.frameOptions().disable();

		// maximum session allowed session
		http.sessionManagement().maximumSessions(5).sessionRegistry(sessionRegistry());
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}
}
