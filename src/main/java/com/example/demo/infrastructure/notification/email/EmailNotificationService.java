package com.example.demo.infrastructure.notification.email;

import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.example.demo.application.aop.LogMethodExecution;
import com.example.demo.domain.Employee;

@Component
public class EmailNotificationService {

	private static final Logger LOG = Logger.getLogger(EmailNotificationService.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@LogMethodExecution
	public void sendSimpleMail(Employee employee) {
		if (CollectionUtils.isEmpty(employee.getContactDetails())) {
			return;
		}
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(employee.getContactDetails().get(0).getEmailId());
		mailMessage.setSubject("Welcome a board");
		mailMessage.setFrom("kaustuv.maji.job@gmail.com");
		Template template = velocityEngine.getTemplate("employeeregistration.vm");
		VelocityContext vlc = new VelocityContext();
		vlc.put("firstName", employee.getFirstName());
		vlc.put("lastName", employee.getSecondName());
		vlc.put("department", employee.getDepartment());
		vlc.put("sender", "Admin HR");
		StringWriter stringWriter = new StringWriter();
		template.merge(vlc, stringWriter);
		LOG.debug(stringWriter.toString());
		mailMessage.setText(stringWriter.toString());
		javaMailSender.send(mailMessage);
	}
}
