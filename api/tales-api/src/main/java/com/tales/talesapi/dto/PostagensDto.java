package com.tales.talesapi.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class PostagensDto {

	private String textoPostagem;
	
	private String imagemUrlPostagem;
	
}
