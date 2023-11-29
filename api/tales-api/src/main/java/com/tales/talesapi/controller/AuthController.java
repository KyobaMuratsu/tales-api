package com.tales.talesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.tales.talesapi.dto.LoginDto;
import com.tales.talesapi.dto.TokenDto;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.services.TokenService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private UserService userService;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public List<String> login(@RequestBody LoginDto login) {
		ArrayList<String> TokenAndUser = new ArrayList<>();
		UsernamePasswordAuthenticationToken userPassAuthToken =
				new UsernamePasswordAuthenticationToken(login.getLogin(),
														login.getPassword());
		
		Authentication auth = this.authManager.authenticate(userPassAuthToken);
		var user = (Usuario) auth.getPrincipal();
		
		TokenDto dto = new TokenDto();
		dto.setToken(tokenService.gerarToken(user));
		
		TokenAndUser.add(dto.getToken());
		TokenAndUser.add(login.getLogin());
		
		return TokenAndUser;
	}

    // handler method to handle list of users
	@CrossOrigin
    @GetMapping("/refresh")
    public boolean refreshToken(HttpServletRequest request){
		String[] tokenSplit = request.getHeader("Authorization").split(",");
		String token = tokenSplit[0];
    	return tokenService.validateJwtToken(token);
    }
}
