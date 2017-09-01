package com.example.demo;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleJob {
	private static final Logger logger = Logger.getLogger(ScheduleJob.class);

	@LogMethodExecution
	@Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void run() {
		logger.info(
				"Spring Boot Scheduling job example This message is from scheduled job -> :: " + LocalDateTime.now());
	}
}
