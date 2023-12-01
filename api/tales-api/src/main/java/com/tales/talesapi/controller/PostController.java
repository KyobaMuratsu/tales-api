package com.tales.talesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.PostagensRepository;
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

	@PostMapping("/post")
	public String registrarPublicacao(@RequestBody String postagem, HttpServletRequest request) {
		String[] tokenSplit = request.getHeader("Authorization").split(",");
		String id = tokenSplit[1];
		
		System.out.println(usuarioRepo.findByMatricula(id));
		System.out.println(id);
		
		
		return "Post://Success";
	}
	
}
