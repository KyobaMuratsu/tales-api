package com.tales.talesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
	UsuarioRepository userRepo;
	
	@GetMapping
	public List<Usuario> getUsers() {
		List<Usuario> users = new ArrayList<Usuario>();
		users.add(userRepo.findAll().get(0));
		return users;
	}
	
}
