package com.tales.talesapi.repositories;

import java.util.List;

import com.tales.talesapi.dto.UserDto;
import com.tales.talesapi.entities.Usuario;

public interface UserService {
	
    void saveUser(UserDto userDto);

    Usuario findUserByEmail(String email);

    List<Usuario> findAllUsers();
}
