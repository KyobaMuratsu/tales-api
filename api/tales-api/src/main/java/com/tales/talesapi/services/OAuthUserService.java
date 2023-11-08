package com.tales.talesapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tales.talesapi.config.TalesDocumentationConfig;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.entities.valueobjects.GoogleCredentials;
import com.tales.talesapi.mappers.GoogleCredentialMapper;
import com.tales.talesapi.repositories.UsuarioRepository;
import com.tales.talesapi.security.BearerTokenWrapper;

@Service
public class OAuthUserService {

	@Autowired
	private UsuarioRepository userRepo;
	
//	@Autowired
//	public BearerTokenWrapper bearerToken;
	
	@Autowired
	public TalesDocumentationConfig appProp;
	
	private DecodedJWT validateIssuerToken(String token) {
		DecodedJWT decoded = JWT.decode(token);
		
		boolean validIssuer = decoded.getIssuer().toString().equals("https://accounts.google.com");
		boolean validClientSecret = decoded.getAudience().get(0).equals(appProp.getGoogle());
		return validIssuer && validClientSecret ? decoded : null;
	}
	
//	public GoogleCredentials getGoogleCredencial() {
//		DecodedJWT valid = validateIssuerToken(bearerToken.getToken());
//		
//		if(valid == null) {
//			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//		}
//		return GoogleCredentialMapper.toValueObject(valid);
//	}
//	
//	public Optional<Usuario> getUserLoggedIn(){
//		GoogleCredentials credentials = getGoogleCredencial();
//		
//		return userRepo.findByEmail(credentials.getEmail());
//	}
	
	
	
}
