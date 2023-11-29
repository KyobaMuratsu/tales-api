package com.tales.talesapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tales.talesapi.entities.Usuario;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public Usuario findByEmail(String email);
	public Usuario findByMatricula(String matricula);
}
