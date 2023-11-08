package com.tales.talesapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tales.talesapi.repositories.UsuarioRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		//Usando findByMatricula substituindo findByLogin
		return userRepo.findByMatricula(username);
	}
}