package com.example.demo.domain.security.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.demo.domain.event.AppUserEvent;
import com.example.demo.domain.security.AppUser;

@Component
public class AppUserEventListner implements ApplicationListener<AppUserEvent> {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	UserRepository userRepository;

	@Override
	public void onApplicationEvent(AppUserEvent event) {
		logger.debug("Register users");
		AppUser user = userRepository.save(event.getAppUser());
		logger.debug("new user " + user + "registered successfully");
	}
}
