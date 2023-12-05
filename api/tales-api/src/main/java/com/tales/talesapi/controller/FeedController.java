package com.tales.talesapi.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Tag;
import com.tales.talesapi.entities.Usuario;  // Importe a entidade do usuário
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.repositories.UsuarioRepository;
import com.tales.talesapi.services.PostagemService;
import com.tales.talesapi.services.TagService;

import jakarta.servlet.http.HttpServletRequest; 

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FeedController {

    @Autowired
    private PostagemService postService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService usuarioService;  // Injete o serviço do usuário
    
    @Autowired
    private UsuarioRepository userRepo;

    @GetMapping("/usertags")
    public ResponseEntity<?> getUserTags(HttpServletRequest request) {
        String[] tokenSplit = request.getHeader("Authorization").split(",");
        String id = tokenSplit[1];

        Usuario user = userRepo.findByMatricula(id);

        if (user != null) {
            Set<Tag> userTags = user.getTags();
            return ResponseEntity.ok(userTags);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
