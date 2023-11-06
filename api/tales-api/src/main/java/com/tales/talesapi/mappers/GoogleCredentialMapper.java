package com.tales.talesapi.mappers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.entities.valueobjects.GoogleCredentials;

public class GoogleCredentialMapper {
	public static GoogleCredentials toValueObject(DecodedJWT jwt) {
		return GoogleCredentials.builder()
			.name(jwt.getClaim("name").asString())
			.email(jwt.getClaim("email").asString())
			.picture(jwt.getClaim("picture").asString())
			.build();
	}
	
	public static Usuario toUsuario(GoogleCredentials credentials) {
		return Usuario.builder()
				.nome(credentials.getName())
				.email(credentials.getEmail())
				.picUrl(credentials.getPicture())
				.build();
	}
}
