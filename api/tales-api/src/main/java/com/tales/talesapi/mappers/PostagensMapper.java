package com.tales.talesapi.mappers;

import com.tales.talesapi.dto.PostagensDto;
import com.tales.talesapi.entities.Postagens;

public class PostagensMapper {

	public static PostagensDto toDto(Postagens post) {
		return PostagensDto.builder()
				.textoPostagem(post.getTextoPostagem())
				.imagemUrlPostagem(post.getImagemUrlPostagem())
				.Usuarioid(post.getUsuario().getId())
				.picUrl(post.getUsuario().getPicUrl())
				.userName(post.getUsuario().getNome())
				.build();
	}
	
}
