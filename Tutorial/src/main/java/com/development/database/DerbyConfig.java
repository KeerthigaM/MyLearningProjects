package com.development.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("file:application.properties")
public class DerbyConfig {
	
	@Value("${derbyURL}")
	private String derbyURL;

	public String getDerbyURL() {
		return derbyURL;
	}
	public void setDerbyURL(String derbyURL) {
		this.derbyURL = derbyURL;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
