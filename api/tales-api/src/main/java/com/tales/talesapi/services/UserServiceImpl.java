package com.tales.talesapi.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tales.talesapi.dto.PostagemDto;
import com.tales.talesapi.dto.UserDto;
import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.repositories.UsuarioRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UsuarioRepository userRepository;
//    private RoleRepository roleRepository;
	
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UsuarioRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @JsonProperty
    public void saveUser(UserDto userDto) {
        Usuario user = new Usuario();
        List<Postagens> postagens = new ArrayList<>();
        user.setMatricula(userDto.getMatricula());
        // encrypt the password using spring security
        user.setSenha(passwordEncoder.encode(userDto.getSenha()));
        user.setCriadoEm(LocalDateTime.now());
        
        user.setPostagens(postagens);

//        Role role = roleRepository.findByName("ROLE_ADMIN");
//        if(role == null){
//            role = checkRoleExist();
//        }
//        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    public Usuario findUserByMatricula(String matricula) {
        return userRepository.findByMatricula(matricula);
    }

    public List<Usuario> findAllUsers() {
        List<Usuario> users = userRepository.findAll();
        return users;
    }

	@Override
	public Usuario findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public void savePostagem(String usuarioId, PostagemDto postagemDto) {
		Usuario usuario = userRepository.findByMatricula(usuarioId);
		Postagens postagem = new Postagens( postagemDto.getTextoPostagem(), postagemDto.getImagemUrlPostagem(), LocalDateTime.now());
		postagem.setUsuario(usuario);
		usuario.getPostagens().add(postagem);
		userRepository.save(usuario);
		
	}

//    private Usuario mapToUserDto(Usuario user){
//        Usuario userDto = new Usuario();
//        String[] str = user.getNome().split(" ");
//        userDto.setNome(str[0]);
//        userDto.setEmail(user.getEmail());
//        return userDto;
//    }
//}
//    private Role checkRoleExist(){
//        Role role = new Role();
//        role.setName("ROLE_ADMIN");
//        return roleRepository.save(role);
//}
}
