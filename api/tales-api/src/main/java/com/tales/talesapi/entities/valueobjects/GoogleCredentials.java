package com.tales.talesapi.entities.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class GoogleCredentials {
	private String name;
	
	private String email;
	
	private String picture;
}
