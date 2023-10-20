package com.tales.talesapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class LoginRequest {

	@NotBlank
	@Email
	@Getter @Setter
	private String email;
	
	@NotBlank
	@Getter @Setter
	private String senha;
}
