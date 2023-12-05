package com.tales.talesapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Tag;

public interface PostagensRepository extends JpaRepository<Postagens, Integer> {
	List<Postagens> findByTagsIn(List<Tag> tags);

}
