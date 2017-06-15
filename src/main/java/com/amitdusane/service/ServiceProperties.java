package com.amitdusane.service;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="Person.service", ignoreUnknownFields=false)
@Component
public class ServiceProperties {
	
	
	
	@NotNull
	private String name = "Person Microservice";
	
	@NotNull
	private String description = "Person Description";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}
