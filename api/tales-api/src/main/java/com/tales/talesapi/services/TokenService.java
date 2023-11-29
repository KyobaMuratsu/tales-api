package com.tales.talesapi.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tales.talesapi.entities.Usuario;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;


@Service
public class TokenService {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
	private String secret = "tales";
	private Instant dataExpiracao = LocalDateTime.now()
			.plusHours(5)
			.toInstant(ZoneOffset.of("-03:00"));
	
	public String gerarToken(Usuario user) {
		var algoritimo = Algorithm.HMAC256(secret);
		return JWT.create()
				.withIssuer("Usuario")
				.withSubject(user.getUsername())
				.withClaim("id", user.getId())
				.withExpiresAt(dataExpiracao)
				.sign(algoritimo);
	}
	
	
	
	public boolean validateJwtToken(String authToken) {
		    try {
		    var algoritimo = Algorithm.HMAC256(secret);
		      JWT.require(algoritimo)
		      	.withIssuer("Usuario")
		      	.build()
		      	.verify(authToken);
		      
		      return true;
		      
		    } catch (MalformedJwtException e) {
		      logger.error("Invalid JWT token: {}", e.getMessage());
		    } catch (ExpiredJwtException e) {
		      logger.error("JWT token is expired: {}", e.getMessage());
		    } catch (UnsupportedJwtException e) {
		      logger.error("JWT token is unsupported: {}", e.getMessage());
		    } catch (IllegalArgumentException e) {
		      logger.error("JWT claims string is empty: {}", e.getMessage());
		    }

		    return false;
		  }

	
}
