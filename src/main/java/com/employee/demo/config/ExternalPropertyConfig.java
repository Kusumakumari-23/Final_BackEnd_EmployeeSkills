package com.employee.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
//@PropertySource(value= "file:F:\\Delta\\External Properties\\newApplication.properties")
@PropertySource(ignoreResourceNotFound = true, value= "--spring.config.location= classpath:F:\\Delta\\External Properties\\newApplication.properties")
public class ExternalPropertyConfig {

	@Autowired
	private Environment env;
	
	//@Value("#{'${allowedOrigins}'.split(',')}")
	private List<String> allowedOrigins = new ArrayList<>();

	public List<String> getAllowedOrigins() {
		return allowedOrigins;
	}
	
	public String getPropValue(String property) {
		return env.getProperty(property);
	}

	public void setAllowedOrigins(List<String> allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}
	
	
}
