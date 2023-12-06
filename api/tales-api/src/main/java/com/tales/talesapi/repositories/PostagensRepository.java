package com.tales.talesapi.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Tag;

public interface PostagensRepository extends JpaRepository<Postagens, Integer> {
	
	List<Postagens> findByTagsIn(Set<Tag> tags);

}
