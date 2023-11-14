package com.tales.talesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tales.talesapi.dto.LoginDto;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.services.TokenService;

@RestController
@RequestMapping("/api/")
public class LoginController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService token;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginDto login) {
		UsernamePasswordAuthenticationToken userPassAuthToken =
				new UsernamePasswordAuthenticationToken(login.getLogin(),
														login.getPassword());
		
		Authentication auth = this.authManager.authenticate(userPassAuthToken);
		var user = (Usuario) auth.getPrincipal();
		
		return token.gerarToken(user);
	}
	
}
