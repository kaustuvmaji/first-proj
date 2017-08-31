package com.example.demo;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfigurerAdapter {
	/**
	 * Using simple spring cache.
	 * @return
	 */
//	@Bean
	CacheManager SimpleCacheManager() {
		 return new ConcurrentMapCacheManager("employeeCache");
	}
}
