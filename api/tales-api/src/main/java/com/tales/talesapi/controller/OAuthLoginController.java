package com.tales.talesapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/OAuthlogin")
public class OAuthLoginController {

	@PostMapping("/{token}")
	public String login(@PathVariable String token) {
		DecodedJWT decoded = JWT.decode(token);
		return decoded.getClaim("name").asString();
	}
	
	
	
}
