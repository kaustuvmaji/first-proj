package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * 
 * @author KMaji
 *
 */
public class AppUser /*extends User*/{

	private String userName;
	private String password;
	private List<String> role;

	public String[] getRole() {
		String[] roles = new String[role.size()];
		if (!CollectionUtils.isEmpty(role)) {
			int count = 0;
			for (String each : role) {
				roles[count++] = each;
			}
			return roles;
		}
		return null;
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
