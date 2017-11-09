package com.example.demo.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.example.demo.domain.event.EmailMessage;
import com.example.demo.domain.event.NotificationEvent;

@Component
public class NotificationEventPublisher implements ApplicationEventPublisherAware {

	// @Autowired
	ApplicationEventPublisher aep;

	public void publish(final EmailMessage message) {
		System.out.println("Publishing emailNotification event. ");
		NotificationEvent customSpringEvent = new NotificationEvent(this, message);
		aep.publishEvent(customSpringEvent);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		aep = applicationEventPublisher;
	}
}
