package com.tales.talesapi.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tales.talesapi.services.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserConfigController {

	@Autowired
	private UserServiceImpl userService;
	
	@PutMapping("/userConfig")
	public ResponseEntity<String> registrarPublicacao(
	            @RequestPart("picUrl") MultipartFile image,
	            @RequestPart("nome") String nome,
	            @RequestPart("email") String email,
	            @RequestPart("tags") List<String> tags,
	            HttpServletRequest request) {
		String[] tokenSplit = request.getHeader("Authorization").split(",");
	    String id = tokenSplit[1];
	    System.out.println(tags);
	    
	        try {
	            userService.updateUser(id, image, nome, email, tags);
	            return ResponseEntity.ok("Postagem adicionada com sucesso ao usuário");
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Erro ao adicionar: " + e.getMessage());
	        }
	    }
	
}
