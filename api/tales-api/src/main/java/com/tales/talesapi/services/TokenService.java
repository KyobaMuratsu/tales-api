package com.tales.talesapi.services;

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
	
	
	public String gerarToken(Usuario user) {
		return JWT.create()
				.withIssuer("Usuario")
				.withSubject(user.getUsername())
				.withClaim("id", user.getId())
				.withExpiresAt(LocalDateTime.now()
								.plusMinutes(1)
								.toInstant(ZoneOffset.of("-03:00"))
				).sign(Algorithm.HMAC256("secreta"));
	}
	
	 public boolean validateJwtToken(String authToken) {
		    try {
		      Jwts.parserBuilder().build().parse(authToken);
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
