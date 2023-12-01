package com.tales.talesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.tales.talesapi.dto.PostagemDto;
import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.PostagensRepository;
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostagensRepository postagemRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private UserService userService;

	@PostMapping("/post")
	public ResponseEntity<String> registrarPublicacao(@RequestBody PostagemDto postagem, HttpServletRequest request) {
		String[] tokenSplit = request.getHeader("Authorization").split(",");
		String id = tokenSplit[1];

		try {
			userService.savePostagem(id, postagem);
			return ResponseEntity.ok("Postagem adicionada com sucesso ao usuario");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao adicionar: " + e.getMessage());
		}
		
	}
	
}
