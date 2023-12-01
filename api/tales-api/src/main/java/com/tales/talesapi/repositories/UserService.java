package com.tales.talesapi.repositories;

import java.util.List;

import com.tales.talesapi.dto.PostagemDto;
import com.tales.talesapi.dto.UserDto;
import com.tales.talesapi.entities.Usuario;

public interface UserService {
	
	void savePostagem(String usuarioId, PostagemDto postagemDto);
	
    void saveUser(UserDto userDto);

    Usuario findUserByEmail(String email);

    List<Usuario> findAllUsers();
}
