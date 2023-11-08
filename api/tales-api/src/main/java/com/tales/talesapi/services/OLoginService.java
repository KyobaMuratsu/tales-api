package com.tales.talesapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.talesapi.controller.request.SimpleUserResponse;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.entities.valueobjects.GoogleCredentials;
import com.tales.talesapi.mappers.GoogleCredentialMapper;
import com.tales.talesapi.mappers.UserMapper;
import com.tales.talesapi.repositories.UsuarioRepository;

@Service
public class OLoginService {
	
	@Autowired
	public OAuthUserService _usuarioAutenticadoService;

	@Autowired
	public UsuarioRepository _usuarioRepository;

//	public SimpleUserResponse obterUsuario() {
//		Optional<Usuario> logado = _usuarioAutenticadoService.getUserLoggedIn();
//
//		if (logado.isPresent()) {
//			return UserMapper.toSimpleUserResponse(logado.get());
//		}
//		GoogleCredentials credenciais = _usuarioAutenticadoService.getGoogleCredencial();
//
//		Usuario novoUsuario = GoogleCredentialMapper.toUsuario(credenciais);
//
//		_usuarioRepository.save(novoUsuario);
//
//		return UserMapper.toSimpleUserResponse(novoUsuario);
//	}
}
