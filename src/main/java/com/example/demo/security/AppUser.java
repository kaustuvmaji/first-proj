package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author KMaji
 *
 */
public class AppUser{

	private String userName;
	private String password;
	private List<String> role;
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	public String[] getRole() {
		if (null == role || role.isEmpty()) {
			return null;
		}
		return role.toArray(new String[role.size()]);
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AppUser(String userName, String password, String... roles) {
		this.userName = userName;
		this.password = password;
		if (null != roles)
			this.role = new ArrayList<>();
		for (String eachRole : roles)
			role.add(eachRole);
	}

	@Override
	public String toString() {
		return "AppUser [userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}

}
