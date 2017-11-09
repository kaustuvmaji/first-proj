/**
 * 
 */
package com.example.demo.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.demo.domain.security.service.UserRepository;

/**
 * This class provides the username password authentication implementation.
 * 
 * @author KMaji
 *
 */
// @Configuration
@Component
public class RestAppAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOG = Logger.getLogger(RestAppAuthenticationProvider.class);

	Map<String, AppUser> users = new HashMap<>();
	
	@Autowired
	UserRepository userRepository;

	/**
	 * 
	 */
	public RestAppAuthenticationProvider() {
		users.put("kaustuv", new AppUser("kaustuv", "pass@123", AppUser.ROLE_ADMIN, AppUser.ROLE_USER));
		users.put("user", new AppUser("user", "user@123", AppUser.ROLE_USER));
		users.put("admin", new AppUser("admin", "admin@123", AppUser.ROLE_ADMIN));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// -ve scenario 1 : authentication object should not be null. very rare case.
		// mostly
		// configuration issues
		if (null == authentication) {
			return null;
		}

		// -ve scenario 2 : authentication name and credentials should not be null. very
		// rare
		// case. mostly configuration issues
		if (null == authentication.getName() && null == authentication.getCredentials()) {
			return null;
		}

		String user = authentication.getName().trim();
		String password = authentication.getCredentials().toString().trim();
		com.example.demo.domain.security.AppUser appuser = userRepository.findByUserName(user);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Security user detail { " + appuser + " }");
		}
		String[] roles = appuser.getRole();

		// -ve scenario 3 : user with empty roles is not eligible to access roles based
		// services :(
		if (null == roles || 0 == roles.length) {
			return null;
		}
		Collection<GrantedAuthority> grantedAuths = AuthorityUtils.createAuthorityList(roles);
		return new UsernamePasswordAuthenticationToken(new User(user, password, grantedAuths), password, grantedAuths);
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
		// only username password plaintext token is used to secure this spring boot
		// rest app. in this step we are registering the type of authentication this
		// RestAppAuthenticationProvider class supports.
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
