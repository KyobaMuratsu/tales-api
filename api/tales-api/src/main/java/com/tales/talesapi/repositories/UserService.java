package com.tales.talesapi.repositories;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.tales.talesapi.dto.PostagemDto;
import com.tales.talesapi.dto.UserDto;
import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Tag;
import com.tales.talesapi.entities.Usuario;

public interface UserService {
	
	void savePostagem(String usuarioId, PostagemDto postagemDto, MultipartFile imagem, List<String> tag) throws IOException;
	
    void saveUser(UserDto userDto);
    
    void updateUser(String matricula, MultipartFile picUrl, String nome, String email, List<String> tag) throws IOException;
    
    Usuario findUserByEmail(String email);
    
    public Set<Tag> getTagByUserId(String userid);

    List<Usuario> findAllUsers();
}
