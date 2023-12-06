package com.tales.talesapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Usuario;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDto {

	private String matricula;
	private String senha;
	private LocalDateTime criadoEm;
	private List<Postagens> postagens;

	
}
