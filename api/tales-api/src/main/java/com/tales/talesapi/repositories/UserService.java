package com.tales.talesapi.repositories;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tales.talesapi.dto.PostagemDto;
import com.tales.talesapi.dto.UserDto;
import com.tales.talesapi.entities.Usuario;

public interface UserService {
	
	void savePostagem(String usuarioId, PostagemDto postagemDto, MultipartFile imagem) throws IOException;
	
    void saveUser(UserDto userDto);

    Usuario findUserByEmail(String email);

    List<Usuario> findAllUsers();
}
