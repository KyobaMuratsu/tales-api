package com.tales.talesapi.mappers;

import com.tales.talesapi.controller.request.SimpleUserResponse;
import com.tales.talesapi.entities.Usuario;

public class UserMapper {
	
	public static SimpleUserResponse toSimpleUserResponse(Usuario user) {
		return SimpleUserResponse.builder()
				.nome(user.getNome())
				.email(user.getEmail())
				.picUrl(user.getPicUrl())
				.build();
	}
	
}
