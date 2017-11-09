package com.example.demo.domain.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.demo.domain.event.AppUserEvent;

@Component
public class AppUserEventListner implements ApplicationListener<AppUserEvent> {

	@Autowired
	UserRepository userRepository;

	@Override
	public void onApplicationEvent(AppUserEvent event) {
		userRepository.save(event.getAppUser());
	}
}
