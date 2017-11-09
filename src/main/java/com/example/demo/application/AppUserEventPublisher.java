package com.example.demo.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.example.demo.domain.event.AppUserEvent;
import com.example.demo.domain.security.AppUser;

@Component
public class AppUserEventPublisher implements ApplicationEventPublisherAware {

	ApplicationEventPublisher aep;

	public void publish(final AppUser applicationUser) {
		System.out.println("Publishing new application user creation event. ");
		AppUserEvent appUserEvent = new AppUserEvent(this, applicationUser);
		aep.publishEvent(appUserEvent);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		aep = applicationEventPublisher;
	}
}
