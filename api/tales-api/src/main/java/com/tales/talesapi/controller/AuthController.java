package com.tales.talesapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tales.talesapi.dto.LoginDto;
import com.tales.talesapi.dto.TokenDto;
import com.tales.talesapi.dto.UserDto;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.services.TokenService;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private UserService userService;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService token;
	
	@CrossOrigin
	@PostMapping("/login")
	public TokenDto login(@RequestBody LoginDto login) {
		UsernamePasswordAuthenticationToken userPassAuthToken =
				new UsernamePasswordAuthenticationToken(login.getLogin(),
														login.getPassword());
		
		Authentication auth = this.authManager.authenticate(userPassAuthToken);
		var user = (Usuario) auth.getPrincipal();
		
		TokenDto dto = new TokenDto();
		dto.setToken(token.gerarToken(user));
		return dto;
	}


    // handler method to handle list of users
    @GetMapping("/users")
    public List<Usuario> users(){
        List<Usuario> users = userService.findAllUsers();
        return users;
    }
}
