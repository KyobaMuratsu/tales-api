package com.tales.talesapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Tag;
import com.tales.talesapi.repositories.PostagensRepository;

@Service
public class PostagemService {
	@Autowired
	private PostagensRepository postRepo;
	
	public List<Postagens> getPostsByTags(List<Tag> tags){
		return postRepo.findByTagsIn(tags);
	}
	
}
