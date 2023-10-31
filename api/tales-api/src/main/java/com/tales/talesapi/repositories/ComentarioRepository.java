package com.tales.talesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tales.talesapi.entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer>  {

}
