package com.gl.planesAndAirfileds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class PlanesAndAirfieldsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanesAndAirfieldsApplication.class, args);
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("messages/messages", "messages/errorMessages");  // name of the resource bundle
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
}
