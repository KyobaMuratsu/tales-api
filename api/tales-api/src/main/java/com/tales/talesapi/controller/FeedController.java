package com.tales.talesapi.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tales.talesapi.dto.PostagensDto;
import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Tag;
import com.tales.talesapi.entities.Usuario;  // Importe a entidade do usuário
import com.tales.talesapi.mappers.PostagensMapper;
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.repositories.UsuarioRepository;
import com.tales.talesapi.services.FeedService;
import com.tales.talesapi.services.PostagemService;
import com.tales.talesapi.services.TagService;

import jakarta.servlet.http.HttpServletRequest;
import net.minidev.json.annotate.JsonIgnore; 

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FeedController {
	
	private static final Logger log = LoggerFactory.getLogger(FeedController.class);

    @Autowired
    private PostagemService postService;

    @Autowired
    private TagService tagService;
    
    @Autowired
    private FeedService feedService;

    @Autowired
    private UserService usuarioService;  // Injete o serviço do usuário
    
    @Autowired
    private UsuarioRepository userRepo;

    
    @GetMapping("/userPosts")
    public ResponseEntity<List<PostagensDto>> getUserPosts(HttpServletRequest request) {
        String[] tokenSplit = request.getHeader("Authorization").split(",");
        String id = tokenSplit[1];
        
        Usuario user = new Usuario();
        user = userRepo.findByMatricula(id);
        
        Set<Tag> tagsUser = usuarioService.getTagByUserId(user.getMatricula());

       List<Postagens> postagens = postService.getPostsByTags(tagsUser);
       for(Tag post : tagsUser) {
           System.out.println(post.getNome());
       }
            
       List<PostagensDto> postDto = postagens.stream().map(PostagensMapper::toDto).toList();
       return ResponseEntity.ok(postDto);

    }

    
    
}
