package com.tales.talesapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.talesapi.entities.Tag;
import com.tales.talesapi.repositories.TagRepository;

@Service
public class TagService {
	
	@Autowired
	private TagRepository tagRepo;
	
	public List<Tag> getTagsByIds(List<Integer> tagIds) {
	    
		try {
	        List<Tag> tags = tagRepo.findAllById(tagIds);
	        tags.removeIf(tag -> tag == null);
	        return tags;
	    } catch (Exception e) {
	        // Trate a exceção ou registre-a conforme necessário
	        throw new RuntimeException("Erro ao buscar tags por IDs", e);
	    }
	}

	public List<Tag> getAllTags() {
	    try {
	        return tagRepo.findAll();
	    } catch (Exception e) {
	        // Trate a exceção ou registre-a conforme necessário
	        throw new RuntimeException("Erro ao buscar todas as tags", e);
	    }
	}
	
}
