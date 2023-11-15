package com.tales.talesapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tales.talesapi.entities.Usuario;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDto {
	
	private String nome;
	private String matricula;
	private String senha;
	private String email;
	
}
