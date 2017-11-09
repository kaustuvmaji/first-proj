package com.example.demo.domain.event;

import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9038695664057743051L;

	public NotificationEvent(Object source, EmailMessage email) {
		super(source);
		this.emailMessage = email;
	}

	public EmailMessage getEmailMessage() {
		return emailMessage;
	}

	private EmailMessage emailMessage;
}
