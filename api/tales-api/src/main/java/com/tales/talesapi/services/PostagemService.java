package com.tales.talesapi.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.talesapi.controller.FeedController;
import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Tag;
import com.tales.talesapi.repositories.PostagensRepository;

@Service
public class PostagemService {
	
	private static final Logger log = LoggerFactory.getLogger(PostagemService.class);
	
	@Autowired
	private PostagensRepository postRepo;
	
	public List<Postagens> getPostsByTags(Set<Tag> tags) {
	    if (tags == null || tags.isEmpty()) {
	        return Collections.emptyList(); // Retorna uma lista vazia para conjunto de tags vazio
	    }
	    
	    List<Postagens> postsWithTags = postRepo.findByTagsIn(tags);
	    
	    for(Postagens post : postsWithTags) {
	    	System.out.println(post.getUsuario().getId());
	    }
	    
	    try {
		    return postsWithTags;
	    }catch (Exception e) {
	    	log.error("Erro ao obter postagens para o usuário", e);
			return Collections.emptyList();
		}
	}

	
}
