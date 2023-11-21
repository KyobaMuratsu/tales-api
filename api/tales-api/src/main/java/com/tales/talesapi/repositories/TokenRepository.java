package com.tales.talesapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.tales.talesapi.entities.Token;
import com.tales.talesapi.entities.Usuario;

public interface TokenRepository extends JpaRepository<Token, Long> {

	Optional<Token> findByToken(String token);
	
	@Modifying
	int deleteByUser(Usuario user);
	
}
