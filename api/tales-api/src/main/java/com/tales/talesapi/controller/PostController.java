package com.tales.talesapi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tales.talesapi.dto.PostagemDto;
import com.tales.talesapi.repositories.PostagensRepository;
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostagensRepository postagemRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private UserService userService;

    @PostMapping("/post")
    public ResponseEntity<String> registrarPublicacao(
            @RequestPart("image") MultipartFile image,
            @RequestPart("textoPostagem") String textoPostagem,
            @RequestPart("tags") List<String> tags,
            HttpServletRequest request) {
        String[] tokenSplit = request.getHeader("Authorization").split(",");
        String id = tokenSplit[1];
        PostagemDto postDTO = new PostagemDto();
        postDTO.setTextoPostagem(textoPostagem);

        try {
            userService.savePostagem(id, postDTO, image, tags);
            return ResponseEntity.ok("Postagem adicionada com sucesso ao usuário");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao adicionar: " + e.getMessage());
        }
    }
}
