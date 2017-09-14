package com.example.demo.restinterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * http://www.baeldung.com/spring-http-logging
 */
@Configuration
public class LoggingFilterConfig {

	@Bean
	public CommonsRequestLoggingFilter reqLogFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludeClientInfo(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(false);
		filter.setBeforeMessagePrefix("{ Request packet : ");
		filter.setAfterMessageSuffix(" }");
		return filter;
	}

}