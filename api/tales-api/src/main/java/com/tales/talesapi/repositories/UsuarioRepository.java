package com.tales.talesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tales.talesapi.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
