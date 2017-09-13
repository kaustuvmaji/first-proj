/**
 * 
 */
package com.example.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @author KMaji
 *
 */
// @Configuration
@Component
public class RestAppAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOG = Logger.getLogger(RestAppAuthenticationProvider.class);

	Map<String, AppUser> users = new HashMap<>();

	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";

	/**
	 * 
	 */
	public RestAppAuthenticationProvider() {
		users.put("kaustuv", new AppUser("kaustuv", "pass@123", ROLE_ADMIN, ROLE_USER));
		users.put("user", new AppUser("user", "user@123", ROLE_USER));
		users.put("admin", new AppUser("admin", "admin@123", ROLE_ADMIN));
		LOG.info("@@@@@@@@@@");
		LOG.info(users);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String user = authentication.getName().toString().trim();
		LOG.info(user);
		String password = authentication.getCredentials().toString().trim();
		LOG.info(password);
		AppUser appuser = users.get(user);
		LOG.info(appuser);
		String[] roles = appuser.getRole();
		if (roles != null) {
			Collection<GrantedAuthority> grantedAuths = /* new SimpleGrantedAuthority(role.trim()) */ AuthorityUtils
					.createAuthorityList(roles);
			return new UsernamePasswordAuthenticationToken(new User(user, password, grantedAuths), password,
					grantedAuths);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.AuthenticationProvider#supports(
	 * java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
