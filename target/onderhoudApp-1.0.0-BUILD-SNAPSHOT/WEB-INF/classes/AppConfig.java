package com.cimsolutions.onderhoudApp.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cimsolutions.onderhoudApp.service.SampleService;
import com.cimsolutions.onderhoudApp.service.SampleServiceImpl;

@Configuration
public class AppConfig {
	@Bean
	public SampleService getSampleService() {
		return new SampleServiceImpl();
	}
}