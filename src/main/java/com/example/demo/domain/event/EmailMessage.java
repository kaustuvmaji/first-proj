package com.example.demo.domain.event;

import java.util.Map;

public class EmailMessage {

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Map<String, String> getPropertyHolder() {
		return propertyHolder;
	}

	public void setPropertyHolder(Map<String, String> propertyHolder) {
		this.propertyHolder = propertyHolder;
	}

	private String subject;
	private String emailTemplate;
	private String target;
	private Map<String, String> propertyHolder;

	public EmailMessage(String subject, String emailTemplate, String target) {
		super();
		this.subject = subject;
		this.emailTemplate = emailTemplate;
		this.target = target;
	}
}
