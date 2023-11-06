package com.tales.talesapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "app")
@Primary
public class TalesDocumentationConfig {
	public String google = "";
	
	public void setGoogle(String g) {
		System.out.println("config" + g);
		google = g;
	}
	
	public TalesDocumentationConfig setAppProperties() {
		return new TalesDocumentationConfig();
	}
	
	
	
    
}
