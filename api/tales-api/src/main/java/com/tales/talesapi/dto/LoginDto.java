package com.tales.talesapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginDto {

	private String login;
	private String password;
	
}
