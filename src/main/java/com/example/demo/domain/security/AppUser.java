package com.example.demo.domain.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

/**
 * 
 * @author KMaji
 *
 */
@Document(collection = "users")
public class AppUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1473474054889916323L;

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	@Id
	private String documentId;
	private String userName;
	private String password = "pass@123";
	private Set<String> role;
	private boolean lock;

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getRole() {
		if (null == role || role.isEmpty()) {
			return null;
		}
		return role.toArray(new String[role.size()]);
	}

	public void setRole(Set<String> role) {
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

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public AppUser(String userName, String password, String... roles) {
		this.userName = userName;
		if (StringUtils.hasText(password)) {
			this.password = password;
		}
		if (null != roles)
			this.role = new HashSet<>();
		this.role.add(ROLE_USER);
		for (String eachRole : roles)
			role.add(eachRole);
	}
	
	public AppUser() {
	}

	@Override
	public String toString() {
		return "AppUser [userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}

}
