package com.tales.talesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tales.talesapi.payload.LoginRequest;
import com.tales.talesapi.repositories.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private PasswordEncoder senhaEncoder;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> autenticarUsuario(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication auth = authManager.authenticated()
		
	}
	
}
