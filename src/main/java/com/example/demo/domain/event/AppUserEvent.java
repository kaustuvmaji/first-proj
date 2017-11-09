package com.example.demo.domain.event;

import org.springframework.context.ApplicationEvent;

import com.example.demo.domain.security.AppUser;

public class AppUserEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2564934368419110880L;

	public AppUserEvent(Object source, AppUser appuser) {
		super(source);
		this.appUser = appuser;
	}

	private AppUser appUser;

	public AppUser getAppUser() {
		return appUser;
	}

}
