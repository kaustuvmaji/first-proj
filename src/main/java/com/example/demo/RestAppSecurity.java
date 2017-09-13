package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true, order = SecurityProperties.ACCESS_OVERRIDE_ORDER)
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
		 * Step 1. Pure example of inMemoryAuthentication
		 */
		 //	auth.inMemoryAuthentication().withUser("admin").password("admin@123").roles("ADMIN"). // Admin user
		 //			and().withUser("user1").password("user@123").roles("USER"). // local user
		 //			and().withUser("kaustuv").password("pass@123").roles("USER", "ADMIN"); // Myself having both

		/*
		 * Step 2. Example of AuthenticationProvider
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
		// Roles based authentication using url
		/*
		 *  http.httpBasic().and().authorizeRequests().antMatchers("/employee/**").
		 *  hasRole("ADMIN").antMatchers("/**")
		 * .hasRole("USER").and().csrf().disable().headers().frameOptions().disable();
		 */
		http.httpBasic().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable().headers()
				.frameOptions().disable();
	}
}
