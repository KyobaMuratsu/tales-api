package com.tales.talesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tales.talesapi.entities.Tag;
import com.tales.talesapi.entities.Usuario;

public interface TagRepository extends JpaRepository<Tag, Integer> {

	public Tag findByNome(String nome);
}
