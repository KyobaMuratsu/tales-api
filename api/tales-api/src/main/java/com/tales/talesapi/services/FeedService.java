package com.tales.talesapi.services;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Tag;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.PostagensRepository;
import com.tales.talesapi.repositories.UsuarioRepository;

@Service
public class FeedService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostagensRepository postagemRepository;

    public List<Postagens> obterFeedDoUsuario(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario != null) {
            Set<Tag> tagsDoUsuario = usuario.getTags();
            return postagemRepository.findByTagsIn(tagsDoUsuario);
        }

        return Collections.emptyList();
    }
}